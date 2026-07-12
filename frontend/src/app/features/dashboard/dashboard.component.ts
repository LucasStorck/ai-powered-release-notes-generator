import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
    <div class="dashboard">
      <header class="dashboard__header">
        <h1 class="dashboard__title">Dashboard</h1>
        <p class="dashboard__subtitle">Bem-vindo ao AI-Powered Release Notes</p>
      </header>
    </div>
  `,
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {}
