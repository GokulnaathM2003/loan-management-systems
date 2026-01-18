package com.loan.transactionhistoryservice.service;

import com.loan.transactionhistoryservice.entity.Transaction;
import com.loan.transactionhistoryservice.repository.TransactionRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;



import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getTransactions(
            String loanId,
            String type,
            LocalDate fromDate,
            LocalDate toDate
    ) {

        Specification<Transaction> spec =
                (root, query, cb) -> cb.conjunction();

        if (loanId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("loanId"), loanId));
        }

        if (type != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("transactionType"), type));
        }

        if (fromDate != null && toDate != null) {
            spec = spec.and((root, query, cb) ->
                    cb.between(root.get("transactionDate"), fromDate, toDate));
        }

        return repository.findAll(spec);
    }
}