package com.bank.paymentservice.service;

import com.bank.paymentservice.model.PartialPrepayment;
import com.bank.paymentservice.repository.PartialPrepaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PartialPrepaymentService {

    private final PartialPrepaymentRepository repository;

    public PartialPrepaymentService(PartialPrepaymentRepository repository) {
        this.repository = repository;
    }

    public PartialPrepayment makePrepayment(Long loanId, Double amount, Double remainingPrincipal) {

        PartialPrepayment p = new PartialPrepayment();
        p.setLoanId(loanId);
        p.setPrepaymentAmount(amount);             // ✅ correct field
        p.setRemainingPrincipal(remainingPrincipal);
        p.setPrepaymentDate(LocalDate.now());      // ✅ correct field

        return repository.save(p);
    }

    public List<PartialPrepayment> getPrepaymentHistory(Long loanId) {
        return repository.findByLoanId(loanId);
    }
}
