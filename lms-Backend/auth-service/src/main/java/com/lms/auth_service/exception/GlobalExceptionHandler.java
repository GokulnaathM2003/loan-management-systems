package com.lms.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ❌ Wrong username or password
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse(
                HttpStatus.UNAUTHORIZED,
                "Invalid username or password"
        );
    }

    // ❌ Custom runtime errors (User not found, Customer not found, etc.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // ❌ Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
   

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<String> handleUserExists(UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        @ExceptionHandler(CustomerNotFoundException.class)
        public ResponseEntity<String> handleCustomer(CustomerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

     
    


    // ❌ Fallback (any unexpected error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please try again."
        );
    }

    // 🔧 Helper method
    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status,
            String message
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);

        return new ResponseEntity<>(response, status);
    }
}
