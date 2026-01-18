import { Component, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form!: FormGroup;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      role: ['USER'],
      customerId: ['', Validators.required]
    });
  }

  register(): void {
    // 🔴 Form validation
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    // 🔴 Password match validation
    if (this.form.value.password !== this.form.value.confirmPassword) {
      alert('Passwords do not match');
      return;
    }

    // ✅ Payload sent to backend (NO confirmPassword)
    const payload = {
      username: this.form.value.username,
      email: this.form.value.email,
      password: this.form.value.password,
      role: this.form.value.role,
      customerId: Number(this.form.value.customerId)
    };

    this.loading = true;

    this.authService.register(payload).subscribe({
      next: () => {
        // ✅ Backend returned 201 Created
        this.loading = false;
        alert('Registration successful');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.loading = false;
        console.error('Registration error:', err);

        // ❌ Show error only if real failure
        alert('Registration failed (user may already exist)');
      }
    });
  }
}
