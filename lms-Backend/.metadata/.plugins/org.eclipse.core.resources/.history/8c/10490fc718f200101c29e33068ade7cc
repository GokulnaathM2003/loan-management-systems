package com.bank.paymentservice.controller;

import com.bank.paymentservice.model.EmiPayment;
import com.bank.paymentservice.service.EmiPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class EmiPaymentController {

    private final EmiPaymentService service;

    public EmiPaymentController(EmiPaymentService service) {
        this.service = service;
    }

    // ================= PAY EMI =================
    @PostMapping("/emi")
    public String payEmi(
            @RequestParam Long loanId,
            @RequestParam Double amount) {

        return service.payEmi(loanId, amount);
    }

    // ================= EMI HISTORY =================
    @GetMapping("/emi/{loanId}")
    public List<EmiPayment> getEmiHistory(
            @PathVariable("loanId") Long loanId) {

        return service.getEmiHistory(loanId);
    }
}
