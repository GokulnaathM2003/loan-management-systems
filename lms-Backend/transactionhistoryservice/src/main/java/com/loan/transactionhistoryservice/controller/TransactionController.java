package com.loan.transactionhistoryservice.controller;


import com.loan.transactionhistoryservice.entity.Transaction;
import com.loan.transactionhistoryservice.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getTransactions(
            @RequestParam(required = false) String loanId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate toDate
    ) {
        return service.getTransactions(loanId, type, fromDate, toDate);
    }
}

