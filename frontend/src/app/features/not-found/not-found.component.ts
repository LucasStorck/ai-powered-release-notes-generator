import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink],
  template: `
    <main class="not-found">
      <p class="not-found__code">404</p>
      <h1 class="not-found__title">Página não encontrada</h1>
      <a routerLink="/dashboard" class="not-found__link">← Voltar ao início</a>
    </main>
  `,
  styleUrl: './not-found.component.scss',
})
export class NotFoundComponent {}
