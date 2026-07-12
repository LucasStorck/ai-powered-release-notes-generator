import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../../../core/http/api.service';
import {
  ReleaseNote,
  ReleaseNoteSummary,
  GenerateReleaseNoteRequest,
  PagedResponse,
} from '../../../core/models/release-note.model';

@Injectable({ providedIn: 'root' })
export class ReleaseNotesService {
  private readonly api = inject(ApiService);

  getAll(page = 0, size = 20): Observable<PagedResponse<ReleaseNoteSummary>> {
    return this.api.get<PagedResponse<ReleaseNoteSummary>>('/release-notes', { page, size });
  }

  getById(id: string): Observable<ReleaseNote> {
    return this.api.get<ReleaseNote>(`/release-notes/${id}`);
  }

  generate(request: GenerateReleaseNoteRequest): Observable<ReleaseNote> {
    return this.api.post<ReleaseNote>('/release-notes/generate', request);
  }

  publish(id: string): Observable<ReleaseNote> {
    return this.api.patch<ReleaseNote>(`/release-notes/${id}/publish`, {});
  }

  archive(id: string): Observable<ReleaseNote> {
    return this.api.patch<ReleaseNote>(`/release-notes/${id}/archive`, {});
  }

  delete(id: string): Observable<void> {
    return this.api.delete<void>(`/release-notes/${id}`);
  }
}
