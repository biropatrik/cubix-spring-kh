package hu.cubix.orderservice.patrik.exception;

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

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAddressNotFoundException(AddressNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(OrderAlreadyAddedException.class)
    public ResponseEntity<Map<String, String>> handleOrderAlreadyAddedException(OrderAlreadyAddedException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFoundException(OrderNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler(NotCorrectStatusException.class)
    public ResponseEntity<Map<String, String>> handleNotCorrectStatusException(NotCorrectStatusException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }
}
