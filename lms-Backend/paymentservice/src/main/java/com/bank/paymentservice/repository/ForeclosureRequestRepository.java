package com.bank.paymentservice.repository;

import com.bank.paymentservice.model.ForeclosureRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForeclosureRequestRepository
        extends JpaRepository<ForeclosureRequest, Long> {

    // ✅ ALWAYS use TOP + ORDER BY (prevents NonUniqueResultException)
    Optional<ForeclosureRequest>
        findTopByLoanIdAndStatusOrderByRequestDateDesc(
                Long loanId,
                String status
        );

    // (Optional – for admin/history)
    Optional<ForeclosureRequest>
        findTopByLoanIdOrderByRequestDateDesc(Long loanId);
}
