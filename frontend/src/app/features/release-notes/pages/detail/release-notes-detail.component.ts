import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-release-notes-detail',
  standalone: true,
  template: `
    <div class="release-notes-detail">
      <h1>Release Note: {{ id }}</h1>
    </div>
  `,
  styleUrl: './release-notes-detail.component.scss',
})
export class ReleaseNotesDetailComponent {
  @Input() id!: string; // Injetado automaticamente via withComponentInputBinding()
}
