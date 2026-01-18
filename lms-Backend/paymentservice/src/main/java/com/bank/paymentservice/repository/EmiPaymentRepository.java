package com.bank.paymentservice.repository;

import com.bank.paymentservice.model.EmiPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmiPaymentRepository extends JpaRepository<EmiPayment, Long> {

    // ✅ Ordered EMI history (BEST PRACTICE)
    List<EmiPayment> findByLoanIdOrderByPaymentDateAsc(Long loanId);
}
