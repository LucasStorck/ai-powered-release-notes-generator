import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'relativeDate', standalone: true })
export class RelativeDatePipe implements PipeTransform {
  transform(value: string | Date): string {
    const date = new Date(value);
    const now = new Date();
    const diffMs = now.getTime() - date.getTime();
    const diffSeconds = Math.floor(diffMs / 1000);
    const diffMinutes = Math.floor(diffSeconds / 60);
    const diffHours = Math.floor(diffMinutes / 60);
    const diffDays = Math.floor(diffHours / 24);
    const diffWeeks = Math.floor(diffDays / 7);
    const diffMonths = Math.floor(diffDays / 30);

    if (diffSeconds < 60)  return 'agora mesmo';
    if (diffMinutes < 60)  return `há ${diffMinutes} min`;
    if (diffHours < 24)    return `há ${diffHours}h`;
    if (diffDays < 7)      return `há ${diffDays} dia${diffDays > 1 ? 's' : ''}`;
    if (diffWeeks < 5)     return `há ${diffWeeks} semana${diffWeeks > 1 ? 's' : ''}`;
    if (diffMonths < 12)   return `há ${diffMonths} mês${diffMonths > 1 ? 'es' : ''}`;

    return date.toLocaleDateString('pt-BR', {
      day: '2-digit', month: 'short', year: 'numeric',
    });
  }
}
