package com.example.lms_loan_service.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "repayment_schedule")
public class RepaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "installment_no", nullable = false)
    private Integer installmentNo;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "emi_amount", nullable = false)
    private Double emiAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RepaymentStatus status;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    // ---------- Getters & Setters ----------

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Integer getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(Integer installmentNo) {
        this.installmentNo = installmentNo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public RepaymentStatus getStatus() {
        return status;
    }

    public void setStatus(RepaymentStatus status) {
        this.status = status;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }
}
