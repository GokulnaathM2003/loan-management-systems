import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ForeclosureService {

  private readonly baseUrl =
    `${environment.apiBaseUrl}/payments/foreclosure`;

  constructor(private http: HttpClient) {}

  requestForeclosure(loanId: number): Observable<string> {
    return this.http.post(
      `${this.baseUrl}/request?loanId=${loanId}`,
      {},
      { responseType: 'text' }
    );
  }

  approveForeclosure(requestId: number): Observable<number> {
    return this.http.post<number>(
      `${this.baseUrl}/approve?requestId=${requestId}`,
      {}
    );
  }

  payForeclosure(requestId: number): Observable<string> {
    return this.http.post(
      `${this.baseUrl}/pay?requestId=${requestId}`,
      {},
      { responseType: 'text' }
    );
  }
}
