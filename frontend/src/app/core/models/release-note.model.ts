export interface ReleaseNote {
  id: string;
  title: string;
  version: string;
  content: string;
  tags: string[];
  status: ReleaseNoteStatus;
  createdAt: string;
  updatedAt: string;
  generatedByAI: boolean;
}

export type ReleaseNoteStatus = 'draft' | 'published' | 'archived';

export interface ReleaseNoteSummary {
  id: string;
  title: string;
  version: string;
  status: ReleaseNoteStatus;
  createdAt: string;
  generatedByAI: boolean;
}

export interface GenerateReleaseNoteRequest {
  repositoryUrl: string;
  fromTag: string;
  toTag: string;
  additionalContext?: string;
}

export interface PagedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  page: number;
  size: number;
}
