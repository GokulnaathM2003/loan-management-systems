package com.bank.paymentservice.service;

import com.bank.paymentservice.client.LoanServiceClient;
import com.bank.paymentservice.model.EmiPayment;
import com.bank.paymentservice.repository.EmiPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmiPaymentService {

    private final EmiPaymentRepository repo;
    private final LoanServiceClient loanClient;

    public EmiPaymentService(EmiPaymentRepository repo,
                             LoanServiceClient loanClient) {
        this.repo = repo;
        this.loanClient = loanClient;
    }

    // ================= PAY EMI =================
    public String payEmi(Long loanId, Double amount) {

        // 1️⃣ Call Loan Service (Feign)
        Double remaining = loanClient.applyEmi(loanId, amount);

        // 2️⃣ Save EMI record
        EmiPayment e = new EmiPayment();
        e.setLoanId(loanId);
        e.setAmountPaid(amount);
        e.setPaymentDate(LocalDate.now());
        e.setRemainingBalance(remaining);

        repo.save(e);

        return "EMI paid. Remaining balance = " + remaining;
    }

    // ================= EMI HISTORY =================
    public List<EmiPayment> getEmiHistory(Long loanId) {
        return repo.findByLoanIdOrderByPaymentDateAsc(loanId);
    }
}
