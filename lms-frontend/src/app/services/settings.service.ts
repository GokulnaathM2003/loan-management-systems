// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
 
// @Injectable({
//   providedIn: 'root'
// })
// export class SettingsService {
 
//   private baseUrl = 'http://localhost:8083/api/settings';
 
//   constructor(private http: HttpClient) {}
 
//   getProfile(customerId: string) {
//     return this.http.get(`${this.baseUrl}/profile/${customerId}`);
//   }
 
//   updateProfile(customerId: string, data: any) {
//     return this.http.put(`${this.baseUrl}/profile/${customerId}`, data);
//   }
 
//   changePassword(customerId: string, data: any) {
//     return this.http.put(
//       `${this.baseUrl}/password/${customerId}`,
//       data,
//       { responseType: 'text' }
//     );
//   }
// }
 
 