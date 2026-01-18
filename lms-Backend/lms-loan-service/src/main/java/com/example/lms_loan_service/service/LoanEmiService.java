package com.example.lms_loan_service.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms_loan_service.model.*;
import com.example.lms_loan_service.repository.*;

@Service
public class LoanEmiService {

    private final LoanRepository loanRepository;
    private final RepaymentScheduleRepository repaymentRepo;

    public LoanEmiService(LoanRepository loanRepository,
                          RepaymentScheduleRepository repaymentRepo) {
        this.loanRepository = loanRepository;
        this.repaymentRepo = repaymentRepo;
    }

    @Transactional
    public Double applyEmi(Long loanId, Double amount) {

        // 1️⃣ Fetch loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new RuntimeException("Loan not active");
        }

        // 2️⃣ Fetch NEXT pending installment
        RepaymentSchedule schedule = repaymentRepo
                .findFirstByLoanIdAndStatusOrderByInstallmentNoAsc(
                        loanId, RepaymentStatus.PENDING
                )
                .orElseThrow(() ->
                        new RuntimeException("No pending EMI found")
                );

        // 3️⃣ Validate EMI amount
        if (!schedule.getEmiAmount().equals(amount)) {
            throw new RuntimeException("Invalid EMI amount");
        }

        // 4️⃣ Mark installment PAID
        schedule.setStatus(RepaymentStatus.PAID);
        schedule.setPaidDate(LocalDate.now());
        repaymentRepo.save(schedule);

        // 5️⃣ Reduce loan balance
        double updated = loan.getRemainingPrincipal() - amount;
        loan.setRemainingPrincipal(updated);

        if (updated == 0) {
            loan.setStatus(LoanStatus.CLOSED);
        }

        loanRepository.save(loan);

        return updated;
    }
}
