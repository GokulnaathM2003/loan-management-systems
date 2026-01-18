import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from '../../pages/profile/profile.component';
import { SecurityComponent } from '../../pages/security/security.component';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [
    CommonModule,
    ProfileComponent,
    SecurityComponent
  ],
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {}
