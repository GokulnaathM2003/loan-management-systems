package com.example.lms_loan_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
    name = "payment-service",
    url = "http://localhost:8085"
)
public interface PaymentServiceClient {

    // ADMIN approves foreclosure by LOAN ID
    @PutMapping("/payments/foreclosure/approve/loan/{loanId}")
    Double approveForeclosure(
            @PathVariable Long loanId
    );
}
