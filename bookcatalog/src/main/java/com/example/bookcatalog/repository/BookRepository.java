package com.example.bookcatalog.repository;

import com.example.bookcatalog.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Recherche par ISBN
    Book findByIsbn(String isbn);

    // Recherche par titre ou auteur
    List<Book> findByTitleContainingOrAuthorContaining(String title, String author);

    // Recherche par année de publication
    List<Book> findByPublicationDateYear(int year);

    // Pagination et tri des livres
    Page<Book> findAll(PageRequest pageRequest);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPublicationYear(int year);
}
