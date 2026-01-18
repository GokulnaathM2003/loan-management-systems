package com.bank.paymentservice.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeign(FeignException ex) {

        String message = ex.contentUTF8();

        if (message.contains("Loan is already closed")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Loan is already closed"));
        }

        if (message.contains("Loan not found")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Loan not found"));
        }

        if (message.contains("exceeds")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Payment amount exceeds balance"));
        }

        return ResponseEntity
                .status(500)
                .body(Map.of("error", "Loan service error"));
    }
}
