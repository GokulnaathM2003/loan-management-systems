import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ForeclosureService } from '../../services/foreclosure.service';

@Component({
  selector: 'app-foreclosure',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './foreclosure.component.html',
  styleUrls: ['./foreclosure.component.css']
})
export class ForeclosureComponent {

  // DEMO DATA
  loanId = 123;
  loanAccountNumber = 'LA-2024-001234';

  outstandingPrincipal = 1375000;
  accruedInterest = 9792;
  foreclosureCharges = 27500;
  processingFee = 1000;

  reason = '';
  paymentDate = '';
  agreed = false;

  constructor(private foreclosureService: ForeclosureService) {}

  get totalAmount(): number {
    return (
      this.outstandingPrincipal +
      this.accruedInterest +
      this.foreclosureCharges +
      this.processingFee
    );
  }

  submitRequest() {
    if (!this.agreed) {
      alert('Please accept terms');
      return;
    }

    // MOCK CALL
    this.foreclosureService.requestForeclosure(this.loanId)
      .subscribe(() => {
        alert(
          `✅ Foreclosure Successful (Mock)\n\n` +
          `Loan: ${this.loanAccountNumber}\n` +
          `Amount Paid: ₹${this.totalAmount}`
        );
      });
  }

  cancel() {
    this.reason = '';
    this.paymentDate = '';
    this.agreed = false;
  }
}
