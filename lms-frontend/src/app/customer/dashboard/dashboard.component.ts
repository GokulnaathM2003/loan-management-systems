import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  loans: any[] = [];

  summary = {
    totalActive: 0,
    totalOutstanding: 0,
    totalEmi: 0,
    nextEmiDate: '-'
  };

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadDashboardData();
  }

  // ✅ MOCK DATA (NO BACKEND)
  loadDashboardData() {

    // Fake loan list
    const mockLoans = [
      {
        id: 1,
        status: 'ACTIVE',
        outstandingAmount: 50000,
        emiAmount: 2500,
        emiDate: '2026-02-10'
      },
      {
        id: 2,
        status: 'CLOSED',
        outstandingAmount: 0,
        emiAmount: 0,
        emiDate: null
      }
    ];

    this.loans = mockLoans;
    this.calculateSummary(mockLoans);
  }

  calculateSummary(loans: any[]) {

    const activeLoans = loans.filter(
      loan => loan.status === 'ACTIVE'
    );

    // ✅ Total Active Loans
    this.summary.totalActive = activeLoans.length;

    // ✅ Total Outstanding
    this.summary.totalOutstanding = activeLoans.reduce(
      (sum, loan) => sum + loan.outstandingAmount,
      0
    );

    // ✅ Total Monthly EMI
    this.summary.totalEmi = activeLoans.reduce(
      (sum, loan) => sum + loan.emiAmount,
      0
    );

    // ✅ Next EMI Date
    this.summary.nextEmiDate =
      activeLoans.length > 0
        ? activeLoans[0].emiDate
        : '-';
  }

  openLoan(loanId: number) {
    this.router.navigate(
      ['/customer/loan-details'],
      { queryParams: { loanId } }
    );
  }
}
