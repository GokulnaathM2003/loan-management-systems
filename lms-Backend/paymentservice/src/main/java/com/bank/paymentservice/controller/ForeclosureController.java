package com.bank.paymentservice.controller;

import com.bank.paymentservice.service.ForeclosureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/foreclosure")
public class ForeclosureController {

    private final ForeclosureService service;

    public ForeclosureController(ForeclosureService service) {
        this.service = service;
    }

    // =====================================================
    // 1️⃣ USER requests foreclosure (by LOAN ID)
    // =====================================================
    @PostMapping("/request/{loanId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String requestForeclosure(@PathVariable Long loanId) {
        return service.requestForeclosure(loanId);
    }

    // =====================================================
    // 2️⃣ ADMIN approves foreclosure (by LOAN ID)
    // =====================================================
    @PutMapping("/approve/loan/{loanId}")
    public Double approveByLoanId(@PathVariable Long loanId) {
        return service.approveByLoanId(loanId);
    }

    // =====================================================
    // 3️⃣ USER pays foreclosure amount (by REQUEST ID)
    // =====================================================
    @PostMapping("/pay/{requestId}")
    public String payForeclosure(@PathVariable Long requestId) {
        return service.payForeclosure(requestId);
    }
}
