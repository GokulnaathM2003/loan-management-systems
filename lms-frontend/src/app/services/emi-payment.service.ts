import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmiPaymentService {

  private readonly baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  payEmi(loanId: number, amount: number): Observable<string> {
    const params = new HttpParams()
      .set('loanId', loanId)
      .set('amount', amount);

    return this.http.post(
      `${this.baseUrl}/payments/emi/pay`,
      {},
      { params, responseType: 'text' }
    );
  }
}
