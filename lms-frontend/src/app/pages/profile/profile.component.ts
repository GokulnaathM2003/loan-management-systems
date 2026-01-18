import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SettingsService } from '../../services/settings.service';
import { SecurityComponent } from '../security/security.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SecurityComponent   // 🔥 REQUIRED
  ],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  customerId = 'CUST-2024-1001';
  profileForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private settingsService: SettingsService
  ) {}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });
  }

  saveProfile() {
    if (this.profileForm.invalid) return;
    this.settingsService
      .updateProfile(this.customerId, this.profileForm.value)
      .subscribe(() => alert('Profile updated'));
  }
}
