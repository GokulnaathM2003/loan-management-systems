// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class LoanService {

//   // 🔹 Base URL for CUSTOMER APIs
//   private baseUrl = 'http://localhost:8080/customer';

//   constructor(private http: HttpClient) {}

//   /**
//    * 🔹 Get all loans of logged-in customer
//    * Used in:
//    * - Dashboard (My Loans)
//    * - Loan Details (All loans view)
//    */
//   getMyLoans(): Observable<any[]> {
//     return this.http.get<any[]>(`${this.baseUrl}/loans`);
//   }

//   /**
//    * 🔹 Get single loan by ID
//    * Used when clicking a loan card from dashboard
//    */
//   getLoanById(loanId: number): Observable<any> {
//     return this.http.get<any>(`${this.baseUrl}/loans/${loanId}`);
//   }

// }
