package com.example.lms_loan_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms_loan_service.dto.LoanResponseDto;
import com.example.lms_loan_service.model.ForeclosureStatus;
import com.example.lms_loan_service.model.Loan;
import com.example.lms_loan_service.model.LoanStatus;
import com.example.lms_loan_service.repository.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // ================= USER =================

    public List<LoanResponseDto> getLoansByCustomerId(Long customerId) {
        return loanRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= ADMIN =================

    public List<LoanResponseDto> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= FORECLOSURE : USER REQUEST =================
    @Transactional
    public void requestForeclosure(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new RuntimeException("Loan is not active");
        }

        if (loan.getForeclosureStatus() != ForeclosureStatus.NONE) {
            throw new RuntimeException("Foreclosure already requested");
        }

        loan.setForeclosureStatus(ForeclosureStatus.REQUESTED);
        loanRepository.save(loan);
    }

    // ================= FORECLOSURE : CLOSE LOAN (AFTER PAYMENT) =================
    @Transactional
    public void closeLoan(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // 🔐 Safety check
        if (loan.getForeclosureStatus() != ForeclosureStatus.APPROVED) {
            throw new RuntimeException("Loan not approved for foreclosure");
        }

        loan.setStatus(LoanStatus.CLOSED);
        loan.setForeclosureStatus(ForeclosureStatus.COMPLETED);

        loanRepository.save(loan);
    }

    // ================= MAPPER =================
    private LoanResponseDto mapToDto(Loan loan) {
        LoanResponseDto dto = new LoanResponseDto();
        dto.setLoanId(loan.getLoanId());
        dto.setLoanType(loan.getLoanType());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setRemainingPrincipal(loan.getRemainingPrincipal());
        dto.setStatus(loan.getStatus());
        dto.setForeclosureStatus(loan.getForeclosureStatus());
        return dto;
    }
}
