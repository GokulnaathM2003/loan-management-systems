package com.example.lms_loan_service.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms_loan_service.model.RepaymentSchedule;
import com.example.lms_loan_service.model.RepaymentStatus;

public interface RepaymentScheduleRepository
        extends JpaRepository<RepaymentSchedule, Long> {

    boolean existsByLoanId(Long loanId);

    List<RepaymentSchedule> findByLoanId(Long loanId);

    // 🔥 CRITICAL: get next unpaid EMI
    Optional<RepaymentSchedule>
        findFirstByLoanIdAndStatusOrderByInstallmentNoAsc(
            Long loanId, RepaymentStatus status
        );
}
