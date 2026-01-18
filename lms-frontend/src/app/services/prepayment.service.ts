import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrepaymentService {

  constructor() {}

  calculatePrepayment(payload: {
    loanId: number;
    prepaymentAmount: number;
    option: 'REDUCE_EMI' | 'REDUCE_TENURE';
  }): Observable<any> {

    const prepayment = payload.prepaymentAmount;

    const originalOutstanding = 1375000;
    const originalEmi = 14500;
    const originalTenure = 216;

    const newOutstanding = Math.max(
      0,
      originalOutstanding - prepayment
    );

    let newEmi = originalEmi;
    let newTenure = originalTenure;

    if (payload.option === 'REDUCE_EMI') {
      newEmi = Math.max(
        3000,
        originalEmi - Math.floor(prepayment / 200)
      );
    } else {
      newTenure = Math.max(
        12,
        originalTenure - Math.floor(prepayment / 10000)
      );
    }

    const interestSaved = Math.floor(prepayment * 0.15);

    return of({
      newOutstanding,
      newEmi,
      newTenure,
      interestSaved
    });
  }
}
