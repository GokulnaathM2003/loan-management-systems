package com.example.lms_loan_service.exception;

public class InvalidLoanStateException extends RuntimeException {
    public InvalidLoanStateException(String message) {
        super(message);
    }
}
