import { Routes } from '@angular/router';

// ================= AUTH =================
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';

// ================= ADMIN =================
import { DashboardComponent as AdminDashboard } from './admin/dashboard/dashboard.component';

// ================= CUSTOMER =================
import { DashboardComponent as CustomerDashboard } from './customer/dashboard/dashboard.component';
import { CustomerLayoutComponent } from './customer/layout/customer-layout/customer-layout.component';
import { LoanDetailsComponent } from './customer/loan-details/loan-details.component';
import { EmiPaymentComponent } from './customer/emi-payment/emi-payment.component';
import { TransactionHistoryComponent } from './customer/transaction-history/transaction-history.component';
import { PartialPrepaymentComponent } from './customer/prepayment/partial-prepayment.component';
import { ForeclosureComponent } from './customer/foreclosure/foreclosure.component';
// ================= GUARD =================
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [

  // DEFAULT
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // ================= AUTH =================
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // ================= ADMIN =================
  {
    path: 'admin',
    canActivate: [authGuard],
    data: { role: 'ADMIN' },
    children: [
      { path: 'dashboard', component: AdminDashboard },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  // ================= CUSTOMER =================
  {
    path: 'customer',
    component: CustomerLayoutComponent,
    canActivate: [authGuard],
    data: { role: 'USER' },   // 🔥 FIXED (was CUSTOMER)
    children: [
      { path: 'dashboard', component: CustomerDashboard },
      { path: 'loan-details', component: LoanDetailsComponent },
      { path: 'emi-payment', component: EmiPaymentComponent },

      // 🔕 TEMPORARILY DISABLED
      { path: 'prepayment', component: PartialPrepaymentComponent },
       { path: 'foreclosure', component: ForeclosureComponent },
      { path: 'transactions', component: TransactionHistoryComponent },

      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  // FALLBACK
  { path: '**', redirectTo: 'login' }
];
