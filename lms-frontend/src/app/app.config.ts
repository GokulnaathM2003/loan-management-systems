import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import {
  provideHttpClient,
  withInterceptorsFromDi,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { routes } from './app.routes';
import { AuthInterceptor } from './interceptors/auth.interceptor';

// Angular Material
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';

export const appConfig: ApplicationConfig = {
  providers: [

    // ROUTER
    provideRouter(routes),

    // HTTP CLIENT
    provideHttpClient(withInterceptorsFromDi()),

    // AUTH INTERCEPTOR
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },

    // GLOBAL MODULES
    importProvidersFrom(
      FormsModule,
      ReactiveFormsModule, // ✅ REQUIRED FOR formGroup
      BrowserAnimationsModule,

      // MATERIAL MODULES
      MatCardModule,
      MatFormFieldModule,
      MatInputModule,
      MatButtonModule,
      MatIconModule,
      MatToolbarModule
    )
  ]
};
