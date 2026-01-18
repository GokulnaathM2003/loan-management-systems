import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'closedCount',
  standalone: true
})
export class ClosedCountPipe implements PipeTransform {
  transform(loans: any[]): number {
    if (!loans) return 0;
    return loans.filter(loan => loan.status === 'CLOSED').length;
  }
}
