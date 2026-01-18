import { Component } from '@angular/core';
import {
  RouterOutlet,
  RouterLink,
  RouterLinkActive
} from '@angular/router';

@Component({
  selector: 'app-customer-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './customer-layout.component.html',
  styleUrls: ['./customer-layout.component.css']
})
export class CustomerLayoutComponent {}
