package com.example.lms_loan_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms_loan_service.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // 🔥 REQUIRED: matches entity field EXACTLY
    List<Loan> findByCustomerId(Long customerId);


    // ADMIN
    List<Loan> findAll();
}
