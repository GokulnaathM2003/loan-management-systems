import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

 import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../models/transaction.model';

@Component({
  selector: 'app-transaction-history',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  // ======================
  // DATA
  // ======================
  transactions: Transaction[] = [];

  // FILTER FIELDS
  searchText: string = '';
  type: string = '';
  transactionDate: string = '';

  loading = false;

  constructor(
    private transactionService: TransactionService
  ) {}

  // ======================
  // INIT
  // ======================
  ngOnInit(): void {
    this.loadTransactions();
  }

  // ======================
  // LOAD TRANSACTIONS
  // ======================
  loadTransactions(): void {
    this.loading = true;

    this.transactionService.getTransactions(
      this.searchText || undefined,
      this.type || undefined,
      this.transactionDate || undefined
    ).subscribe({
      next: (data) => {
        this.transactions = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        alert('Failed to load transactions');
        this.loading = false;
      }
    });
  }

  // ======================
  // RESET FILTERS
  // ======================
  resetFilters(): void {
    this.searchText = '';
    this.type = '';
    this.transactionDate = '';
    this.loadTransactions();
  }

  // ======================
  // DOWNLOAD RECEIPT
  // ======================
  downloadReceipt(transactionId: string): void {
    this.transactionService.downloadReceipt(transactionId).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `receipt-${transactionId}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: () => {
        alert('Failed to download receipt');
      }
    });
  }
}
