export interface Transaction {
  transactionId: string;
  loanId: string;
  type: string;
  amount: number;
  transactionDate: string;
  status: string;
}
