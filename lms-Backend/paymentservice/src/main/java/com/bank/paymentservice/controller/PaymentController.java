package com.bank.paymentservice.controller;

import com.bank.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // ✅ PARTIAL PREPAYMENT (CORRECT)
    @PostMapping("/partial-prepayment/{loanId}")
    public String partialPrepayment(
            @PathVariable Long loanId,
            @RequestBody Double amount) {

        return service.doPartialPrepayment(loanId, amount);
    }
}
