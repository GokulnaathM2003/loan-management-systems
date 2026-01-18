package com.loan.transactionhistoryservice.repository;



import com.loan.transactionhistoryservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long>,
                JpaSpecificationExecutor<Transaction> {
}

