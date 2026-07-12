import { Routes } from '@angular/router';

export const RELEASE_NOTES_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/list/release-notes-list.component').then(
        (m) => m.ReleaseNotesListComponent
      ),
  },
  {
    path: ':id',
    loadComponent: () =>
      import('./pages/detail/release-notes-detail.component').then(
        (m) => m.ReleaseNotesDetailComponent
      ),
  },
  {
    path: 'generate/new',
    loadComponent: () =>
      import('./pages/generate/release-notes-generate.component').then(
        (m) => m.ReleaseNotesGenerateComponent
      ),
  },
];
