import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    // 🔥 DO NOT attach token for auth endpoints
    if (req.url.includes('/auth/login') || req.url.includes('/auth/register')) {
      return next.handle(req);
    }

    const token = localStorage.getItem('token');

    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(req);
  }
}

