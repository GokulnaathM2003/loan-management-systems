import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ActiveCountPipe } from '../../pipes/active-count.pipe';
import { ClosedCountPipe } from '../../pipes/closed-count.pipe';

@Component({
  selector: 'app-loan-details',
  standalone: true,
  imports: [
    CommonModule,
    ActiveCountPipe,
    ClosedCountPipe
  ],
  templateUrl: './loan-details.component.html',
  styleUrls: ['./loan-details.component.css']
})
export class LoanDetailsComponent implements OnInit {

  loans: any[] = [];
  filteredLoans: any[] = [];
  loading = true;

  // ✅ MOCK LOAN DATA
  private mockLoans = [
    {
      id: 1,
      status: 'ACTIVE',
      loanAmount: 100000,
      outstandingAmount: 50000,
      emiAmount: 2500,
      emiDate: '2026-02-10'
    },
    {
      id: 2,
      status: 'CLOSED',
      loanAmount: 80000,
      outstandingAmount: 0,
      emiAmount: 0,
      emiDate: null
    }
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {

    // ✅ Check if coming from dashboard with loanId
    this.route.queryParams.subscribe(params => {
      const loanId = params['loanId'];

      if (loanId) {
        this.loadLoanById(+loanId);
      } else {
        this.loadAllLoans();
      }
    });
  }

  // ✅ Load all loans (MOCK)
  loadAllLoans() {
    this.loading = true;

    this.loans = this.mockLoans;
    this.filteredLoans = this.mockLoans;
    this.loading = false;
  }

  // ✅ Load single loan (MOCK)
  loadLoanById(loanId: number) {
    this.loading = true;

    const loan = this.mockLoans.find(l => l.id === loanId);

    if (loan) {
      this.loans = [loan];
      this.filteredLoans = [loan];
    } else {
      this.loans = [];
      this.filteredLoans = [];
    }

    this.loading = false;
  }

  // ✅ Filter buttons (ALL / ACTIVE / CLOSED)
  filterLoans(type: 'ALL' | 'ACTIVE' | 'CLOSED') {
    if (type === 'ALL') {
      this.filteredLoans = this.loans;
    } else {
      this.filteredLoans = this.loans.filter(
        loan => loan.status === type
      );
    }
  }
}
