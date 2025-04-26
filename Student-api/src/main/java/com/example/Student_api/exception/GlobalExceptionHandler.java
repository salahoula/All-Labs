package com.example.Student_api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;
public class GlobalExceptionHandler {
    @ControllerAdvice
    public class globalExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> validationErrorHandler(MethodArgumentNotValidException ex, WebRequest request) {
            StringBuilder messages = new StringBuilder();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                messages.append(error.getDefaultMessage()).append(". ");
            });
            ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.toString(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
