import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'activeCount',
  standalone: true
})
export class ActiveCountPipe implements PipeTransform {

  transform(loans: any[]): number {
    if (!loans || loans.length === 0) {
      return 0;
    }

    return loans.filter(
      loan => loan.status === 'ACTIVE'
    ).length;
  }
}
