package com.example.lms_loan_service.controller;

import org.springframework.web.bind.annotation.*;

import com.example.lms_loan_service.service.LoanPrepaymentService;

@RestController
@RequestMapping("/loans")
public class LoanPrepaymentController {

    private final LoanPrepaymentService loanPrepaymentService;

    public LoanPrepaymentController(
            LoanPrepaymentService loanPrepaymentService) {
        this.loanPrepaymentService = loanPrepaymentService;
    }

    // 🔥 PARTIAL PREPAYMENT
    @PutMapping("/{loanId}/partial-prepayment")
    public Double partialPrepayment(
            @PathVariable Long loanId,
            @RequestBody Double amount) {

        return loanPrepaymentService
                .applyPartialPrepayment(loanId, amount);
    }
}
