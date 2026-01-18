package com.bank.paymentservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(Map.of("error", "Internal error: " + ex.getMessage()));
    }
}
