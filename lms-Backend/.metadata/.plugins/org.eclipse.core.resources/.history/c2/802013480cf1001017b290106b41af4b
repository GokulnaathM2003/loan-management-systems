package com.example.lms_loan_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms_loan_service.dto.LoanResponseDto;
import com.example.lms_loan_service.service.LoanService;

@RestController
@RequestMapping("/admin/loans")
public class AdminLoanController {

    private final LoanService loanService;

    public AdminLoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // ADMIN: view all loans
    @GetMapping
    public List<LoanResponseDto> getAllLoans() {
        return loanService.getAllLoans();
    }
}
