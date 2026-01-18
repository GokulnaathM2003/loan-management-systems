package com.bank.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foreclosure_requests")
public class ForeclosureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "status", nullable = false)
    private String status; // PENDING / APPROVED / PAID

    @Column(name = "approved_amount")
    private Double approvedAmount;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
