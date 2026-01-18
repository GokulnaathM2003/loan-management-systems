package com.example.lms_loan_service.controller;

import com.example.lms_loan_service.service.LoanEmiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanEmiController {

    private final LoanEmiService loanEmiService;

    public LoanEmiController(LoanEmiService loanEmiService) {
        this.loanEmiService = loanEmiService;
    }

    @PutMapping("/{loanId}/emi")
    public Double applyEmi(@PathVariable Long loanId,
                           @RequestParam Double amount) {

        return loanEmiService.applyEmi(loanId, amount);
    }
}
