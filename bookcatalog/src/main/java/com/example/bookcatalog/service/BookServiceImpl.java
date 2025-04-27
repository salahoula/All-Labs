package com.example.bookcatalog.service;

import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {   // <<< ICI très important

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {  // <<< Corriger le constructeur
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable(value = "books")
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findBooksPaginated(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    @CachePut(value = "books", key = "#result.id")
    @CacheEvict(value = {"booksByTitle", "booksByAuthor"}, allEntries = true)
    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Caching(
            put = { @CachePut(value = "books", key = "#id") },
            evict = {
                    @CacheEvict(value = "booksByTitle", allEntries = true),
                    @CacheEvict(value = "booksByAuthor", allEntries = true)
            }
    )
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = findBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setPrice(bookDetails.getPrice());
        book.setDescription(bookDetails.getDescription());
        return bookRepository.save(book);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "books", key = "#id"),
                    @CacheEvict(value = "booksByTitle", allEntries = true),
                    @CacheEvict(value = "booksByAuthor", allEntries = true)
            }
    )
    @Transactional
    public void deleteBook(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }
}
