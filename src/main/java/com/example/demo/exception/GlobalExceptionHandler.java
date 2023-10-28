package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // ... otros manejadores de excepción ...

}

class ErrorDetails {
    private String message;

    public ErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
