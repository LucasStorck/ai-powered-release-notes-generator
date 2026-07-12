export interface ApiError {
  status: number;
  message: string;
  timestamp: string;
  path?: string;
  errors?: Record<string, string>;
}
