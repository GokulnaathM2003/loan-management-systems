package com.example.lms_loan_service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms_loan_service.service.ForeclosureApprovalService;

@RestController
@RequestMapping("/admin/foreclosure")
public class AdminForeclosureController {

    private final ForeclosureApprovalService foreclosureService;

    public AdminForeclosureController(ForeclosureApprovalService foreclosureService) {
        this.foreclosureService = foreclosureService;
    }

    // ✅ ADMIN approves foreclosure
    @PutMapping("/{loanId}/approve")
    public Double approveForeclosure(@PathVariable Long loanId) {
        return foreclosureService.approveForeclosure(loanId);
    }
}
