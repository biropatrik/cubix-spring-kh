package hu.cubix.catalogservice.patrik.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(CategoryAlreadyAddedException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyAddedException(CategoryAlreadyAddedException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(DifferentProductIdsException.class)
    public ResponseEntity<Map<String, String>> handleDifferentProductIdsException(DifferentProductIdsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(ProductAlreadyAddedException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyAddedException(ProductAlreadyAddedException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }
}
