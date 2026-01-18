package com.bank.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "loan-service",
        url = "http://localhost:8084"
)
public interface LoanServiceClient {

    // =====================================================
    // EMI PAYMENT
    // =====================================================
    @PutMapping("/loans/{loanId}/emi")
    Double applyEmi(
            @PathVariable Long loanId,
            @RequestBody Double amount
    );

    // =====================================================
    // PARTIAL PREPAYMENT
    // =====================================================
    @PutMapping("/loans/{loanId}/partial-prepayment")
    Double applyPrepayment(
            @PathVariable Long loanId,
            @RequestBody Double amount
    );

    // =====================================================
    // FORECLOSURE REQUEST
    // =====================================================
    @PutMapping("/loans/{loanId}/foreclosure/request")
    void requestForeclosure(
            @PathVariable Long loanId
    );

    // =====================================================
    // CLOSE LOAN (AFTER PAYMENT)
    // =====================================================
    @PutMapping("/loans/{loanId}/close")
    void closeLoan(
            @PathVariable Long loanId
    );

    // =====================================================
    // GET REMAINING BALANCE
    // =====================================================
    @GetMapping("/loans/{loanId}/balance")
    Double getRemainingBalance(
            @PathVariable Long loanId
    );
}
