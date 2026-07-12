import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  standalone: true,
  template: `
    <main class="login-page">
      <div class="login-page__card">
        <h1 class="login-page__title">AI Release Notes</h1>
        <p class="login-page__subtitle">Entre com sua conta para continuar</p>
      </div>
    </main>
  `,
  styleUrl: './login.component.scss',
})
export class LoginComponent {}
