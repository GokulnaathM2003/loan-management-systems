package com.bank.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partial_prepayments")
public class PartialPrepayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prepaymentId;

    private Long loanId;
    private Double prepaymentAmount;
    private LocalDate prepaymentDate;

    @Column(nullable = false)
    private Double remainingPrincipal;

    // getters & setters
    public Long getPrepaymentId() { return prepaymentId; }

    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public Double getPrepaymentAmount() { return prepaymentAmount; }
    public void setPrepaymentAmount(Double prepaymentAmount) { this.prepaymentAmount = prepaymentAmount; }

    public LocalDate getPrepaymentDate() { return prepaymentDate; }
    public void setPrepaymentDate(LocalDate prepaymentDate) { this.prepaymentDate = prepaymentDate; }

    public Double getRemainingPrincipal() { return remainingPrincipal; }
    public void setRemainingPrincipal(Double remainingPrincipal) { this.remainingPrincipal = remainingPrincipal; }
}
