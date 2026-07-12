import { Component } from '@angular/core';

@Component({
  selector: 'app-release-notes-list',
  standalone: true,
  template: `
    <div class="release-notes-list">
      <h1>Release Notes</h1>
    </div>
  `,
  styleUrl: './release-notes-list.component.scss',
})
export class ReleaseNotesListComponent {}
