import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Transaction } from '../models/transaction.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private readonly baseUrl =
    `${environment.apiBaseUrl}/api/transactions`;

  constructor(private http: HttpClient) {}

  // ===============================
  // GET TRANSACTION HISTORY
  // ===============================
  getTransactions(
    loanId?: string,
    type?: string,
    transactionDate?: string
  ): Observable<Transaction[]> {

    let params = new HttpParams();

    if (loanId && loanId.trim()) {
      params = params.set('loanId', loanId.trim());
    }

    if (type && type.trim()) {
      params = params.set('type', type);
    }

    if (transactionDate && transactionDate.trim()) {
      params = params.set('transactionDate', transactionDate);
    }

    return this.http.get<Transaction[]>(
      this.baseUrl,
      { params }
    );
  }

  // ===============================
  // DOWNLOAD RECEIPT
  // ===============================
  downloadReceipt(transactionId: string): Observable<Blob> {
    return this.http.get(
      `${this.baseUrl}/${transactionId}/receipt`,
      { responseType: 'blob' }
    );
  }
}
