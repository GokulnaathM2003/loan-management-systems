package com.example.lms_loan_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms_loan_service.model.*;
import com.example.lms_loan_service.repository.*;

@Service
public class RepaymentScheduleService {

    private final RepaymentScheduleRepository repaymentRepo;
    private final LoanRepository loanRepository;

    public RepaymentScheduleService(
            RepaymentScheduleRepository repaymentRepo,
            LoanRepository loanRepository) {
        this.repaymentRepo = repaymentRepo;
        this.loanRepository = loanRepository;
    }

    // ================= GENERATE SCHEDULE =================
    @Transactional
    public void generateSchedule(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (repaymentRepo.existsByLoanId(loanId)) {
            throw new RuntimeException("Schedule already exists");
        }

        List<RepaymentSchedule> schedules = new ArrayList<>();
        LocalDate startDate = loan.getStartDate();

        for (int i = 1; i <= loan.getTenureMonths(); i++) {
            RepaymentSchedule rs = new RepaymentSchedule();
            rs.setLoanId(loanId);
            rs.setInstallmentNo(i);
            rs.setDueDate(startDate.plusMonths(i));
            rs.setEmiAmount(loan.getEmiAmount());
            rs.setStatus(RepaymentStatus.PENDING);
            schedules.add(rs);
        }

        repaymentRepo.saveAll(schedules);
    }

    // ================= APPLY EMI (🔥 IMPORTANT) =================
    @Transactional
    public void applyEmi(Long loanId) {

        RepaymentSchedule schedule = repaymentRepo
                .findFirstByLoanIdAndStatusOrderByInstallmentNoAsc(
                        loanId, RepaymentStatus.PENDING)
                .orElseThrow(() -> new RuntimeException("No pending EMI"));

        schedule.setStatus(RepaymentStatus.PAID);
        schedule.setPaidDate(LocalDate.now());

        repaymentRepo.save(schedule);
    }
}
