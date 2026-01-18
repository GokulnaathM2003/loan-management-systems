package com.bank.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emi_payments")
public class EmiPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private Long loanId;   // Only store loanId (NO relation)

    private LocalDate paymentDate;

    private Double amountPaid;
    private Double principalPaid;
    private Double interestPaid;
    private Double remainingBalance;

    // getters & setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(Double amountPaid) { this.amountPaid = amountPaid; }

    public Double getPrincipalPaid() { return principalPaid; }
    public void setPrincipalPaid(Double principalPaid) { this.principalPaid = principalPaid; }

    public Double getInterestPaid() { return interestPaid; }
    public void setInterestPaid(Double interestPaid) { this.interestPaid = interestPaid; }

    public Double getRemainingBalance() { return remainingBalance; }
    public void setRemainingBalance(Double remainingBalance) { this.remainingBalance = remainingBalance; }
}
