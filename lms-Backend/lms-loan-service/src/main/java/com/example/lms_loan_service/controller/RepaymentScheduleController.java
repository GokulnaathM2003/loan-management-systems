package com.example.lms_loan_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms_loan_service.model.RepaymentSchedule;
import com.example.lms_loan_service.repository.RepaymentScheduleRepository;
import com.example.lms_loan_service.service.RepaymentScheduleService;

@RestController
@RequestMapping("/loans")
public class RepaymentScheduleController {

    private final RepaymentScheduleService repaymentScheduleService;
    private final RepaymentScheduleRepository repaymentRepo;

    public RepaymentScheduleController(
            RepaymentScheduleService repaymentScheduleService,
            RepaymentScheduleRepository repaymentRepo) {
        this.repaymentScheduleService = repaymentScheduleService;
        this.repaymentRepo = repaymentRepo;
    }

    // ✅ GENERATE repayment schedule (ONE TIME)
    @PostMapping("/{loanId}/repayment-schedule")
    public String generateRepaymentSchedule(@PathVariable Long loanId) {
        repaymentScheduleService.generateSchedule(loanId);
        return "Repayment schedule generated successfully";
    }

    // ✅ VIEW repayment schedule
    @GetMapping("/{loanId}/repayment-schedule")
    public List<RepaymentSchedule> getRepaymentSchedule(
            @PathVariable Long loanId) {
        return repaymentRepo.findByLoanId(loanId);
    }
}
