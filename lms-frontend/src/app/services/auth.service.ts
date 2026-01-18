import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  login(data: { usernameOrEmail: string; password: string }) {
    return this.http.post<any>(
      `${this.baseUrl}/auth/login`,
      data
    );
  }

  // ✅ FINAL FIX
  register(data: any) {
    return this.http.post<HttpResponse<any>>(
      `${this.baseUrl}/auth/register`,
      data,
      {
        observe: 'response'   // 🔥 THIS IS THE KEY
      }
    );
  }

  saveAuth(token: string, role: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
  }

  logout() {
    localStorage.clear();
  }
}
