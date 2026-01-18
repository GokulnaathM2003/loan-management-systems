import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

interface Loan {
  loanId: number;
  loanType: string;
  principalAmount: number;
  interestAmount: number;
  processingFee: number;
  emiAmount: number;
  dueDate: string;
}

@Component({
  selector: 'app-emi-payment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './emi-payment.component.html',
  styleUrls: ['./emi-payment.component.css']
})
export class EmiPaymentComponent implements OnInit {

  // ✅ MOCK LOANS
  loans: Loan[] = [
    {
      loanId: 101,
      loanType: 'HOME',
      principalAmount: 1500000,
      interestAmount: 12000,
      processingFee: 500,
      emiAmount: 14500,
      dueDate: '2026-01-20'
    }
  ];

  selectedLoan: Loan | null = null;

  emiForm!: FormGroup;
  selectedPayment: 'CARD' | 'UPI' | null = null;
  statusMessage = '';

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.emiForm = this.fb.group({
      cardNumber: ['', Validators.required],
      cvv: ['', Validators.required],
      upiId: ['', Validators.required]
    });
  }

  selectLoan(loan: Loan) {
    this.selectedLoan = loan;
    this.statusMessage = '';
  }

  selectPayment(method: 'CARD' | 'UPI') {
    this.selectedPayment = method;
  }

  // ✅ MOCK EMI PAYMENT (NO BACKEND)
  payEmi() {
    if (!this.selectedLoan) {
      this.statusMessage = 'Please select a loan';
      return;
    }

    if (!this.selectedPayment) {
      this.statusMessage = 'Please select a payment method';
      return;
    }

    if (this.emiForm.invalid) {
      this.statusMessage = 'Please enter valid payment details';
      return;
    }

    // Simulate payment success
    this.statusMessage = `EMI of ₹${this.selectedLoan.emiAmount} paid successfully`;

    // Reset after payment
    this.selectedPayment = null;
    this.emiForm.reset();
  }

  cancelPayment() {
    this.selectedPayment = null;
    this.statusMessage = 'Payment cancelled';
  }
}
