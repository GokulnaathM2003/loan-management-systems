import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
 
export const authGuard: CanActivateFn = (route) => {
  const router = inject(Router);
 
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');
  const expectedRole = route.data?.['role'];
 
  if (!token) {
    router.navigate(['/login']);
    return false;
  }
 
  if (expectedRole && role !== expectedRole) {
    router.navigate(['/login']);
    return false;
  }
 
  return true;
};
 
 