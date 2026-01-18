import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PrepaymentService } from '../../services/prepayment.service';

@Component({
  selector: 'app-partial-prepayment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './partial-prepayment.component.html',
  styleUrls: ['./partial-prepayment.component.css']
})
export class PartialPrepaymentComponent implements OnInit {

  prepaymentForm!: FormGroup;

  // STATIC DEMO VALUES (later backend)
  loanId = 123;
  outstandingPrincipal = 1375000;
  currentEmi = 14500;
  remainingTenure = 216;

  // RESULT VALUES
  newOutstanding = 0;
  newEmi = 0;
  newTenure = 0;
  interestSaved = 0;

  // ✅ THIS WAS MISSING
  selectedOption: 'REDUCE_EMI' | 'REDUCE_TENURE' = 'REDUCE_EMI';

  constructor(
    private fb: FormBuilder,
    private prepaymentService: PrepaymentService
  ) {}

  ngOnInit(): void {
    this.prepaymentForm = this.fb.group({
      amount: [50000, [Validators.required, Validators.min(1000)]],
      option: ['REDUCE_EMI', Validators.required]
    });

    this.calculate();
  }

  // ✅ FIXED EARLIER
  addAmount(value: number) {
    const current = this.prepaymentForm.value.amount || 0;
    this.prepaymentForm.patchValue({ amount: current + value });
    this.calculate();
  }

  // ✅ THIS WAS MISSING
  selectOption(option: 'REDUCE_EMI' | 'REDUCE_TENURE') {
    this.selectedOption = option;
    this.prepaymentForm.patchValue({ option });
    this.calculate();
  }

  calculate() {
    if (this.prepaymentForm.invalid) return;

    const payload = {
      loanId: this.loanId,
      prepaymentAmount: this.prepaymentForm.value.amount,
      option: this.prepaymentForm.value.option
    };

    this.prepaymentService.calculatePrepayment(payload).subscribe({
      next: (res) => {
        this.newOutstanding = res.newOutstanding;
        this.newEmi = res.newEmi;
        this.newTenure = res.newTenure;
        this.interestSaved = res.interestSaved;
      },
      error: () => {
        alert('Failed to calculate prepayment');
      }
    });
  }

  proceed() {
    if (this.prepaymentForm.invalid) {
      this.prepaymentForm.markAllAsTouched();
      return;
    }

    alert(
      `Prepayment Successful!\n\n` +
      `Amount: ₹${this.prepaymentForm.value.amount}\n` +
      `New EMI: ₹${this.newEmi}\n` +
      `Interest Saved: ₹${this.interestSaved}`
    );
  }

  cancel() {
    alert('Prepayment cancelled');
  }
}
