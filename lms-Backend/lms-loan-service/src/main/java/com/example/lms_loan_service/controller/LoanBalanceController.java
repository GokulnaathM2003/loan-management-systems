package com.example.lms_loan_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms_loan_service.model.Loan;
import com.example.lms_loan_service.model.LoanStatus;
import com.example.lms_loan_service.repository.LoanRepository;

@RestController
@RequestMapping("/loans")
public class LoanBalanceController {

    private final LoanRepository loanRepository;

    public LoanBalanceController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // 🔥 REQUIRED BY PAYMENT‑SERVICE
    @GetMapping("/{loanId}/balance")
    public Double getRemainingBalance(@PathVariable Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new RuntimeException("Loan is not active");
        }

        return loan.getRemainingPrincipal();
    }
}
