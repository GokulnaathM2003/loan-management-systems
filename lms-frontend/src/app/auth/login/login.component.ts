import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      usernameOrEmail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    console.log('LOGIN CLICKED'); // 🔥 DEBUG LINE

    if (this.form.invalid) {
      console.log('FORM INVALID', this.form.value);
      return;
    }

    const payload = this.form.value;

    this.authService.login(payload).subscribe({
      next: (res: any) => {
        console.log('LOGIN SUCCESS', res);
        this.authService.saveAuth(res.token, res.role);

        this.router.navigate(['/customer/dashboard']);
      },
      error: (err) => {
        console.error('LOGIN ERROR', err);
        alert('Invalid credentials');
      }
    });
  }
}
