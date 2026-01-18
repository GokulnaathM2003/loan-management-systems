package com.bank.paymentservice.service;

import com.bank.paymentservice.client.LoanServiceClient;
import com.bank.paymentservice.model.PartialPrepayment;
import com.bank.paymentservice.repository.PartialPrepaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PaymentService {

    private final LoanServiceClient loanClient;
    private final PartialPrepaymentRepository repo;

    public PaymentService(LoanServiceClient loanClient,
                          PartialPrepaymentRepository repo) {
        this.loanClient = loanClient;
        this.repo = repo;
    }

    @Transactional
    public String doPartialPrepayment(Long loanId, Double amount) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid prepayment amount");
        }

        // 1️⃣ Call Loan Service
        Double remaining = loanClient.applyPrepayment(loanId, amount);

        // 2️⃣ Save Payment record
        PartialPrepayment p = new PartialPrepayment();
        p.setLoanId(loanId);
        p.setPrepaymentAmount(amount);
        p.setPrepaymentDate(LocalDate.now());
        p.setRemainingPrincipal(remaining);

        repo.save(p);

        return "Prepayment successful. Remaining principal = " + remaining;
    }
}
