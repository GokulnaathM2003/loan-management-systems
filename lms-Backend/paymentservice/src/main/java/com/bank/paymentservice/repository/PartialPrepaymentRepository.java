package com.bank.paymentservice.repository;

import com.bank.paymentservice.model.PartialPrepayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartialPrepaymentRepository 
        extends JpaRepository<PartialPrepayment, Long> {

    List<PartialPrepayment> findByLoanId(Long loanId);
}
