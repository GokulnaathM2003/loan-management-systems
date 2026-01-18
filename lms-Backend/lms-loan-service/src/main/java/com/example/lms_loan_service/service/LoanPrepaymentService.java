package com.example.lms_loan_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms_loan_service.model.Loan;
import com.example.lms_loan_service.model.LoanStatus;
import com.example.lms_loan_service.repository.LoanRepository;

@Service
public class LoanPrepaymentService {

    private final LoanRepository loanRepository;

    public LoanPrepaymentService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Double applyPartialPrepayment(Long loanId, Double amount) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new RuntimeException("Loan not active");
        }

        if (amount <= 0) {
            throw new RuntimeException("Invalid prepayment amount");
        }

        if (amount >= loan.getRemainingPrincipal()) {
            throw new RuntimeException(
                "Amount too high. Use foreclosure instead"
            );
        }

        double updated = loan.getRemainingPrincipal() - amount;
        loan.setRemainingPrincipal(updated);

        loanRepository.save(loan);

        return updated;
    }
}
