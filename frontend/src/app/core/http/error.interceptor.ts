import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      switch (error.status) {
        case 401:
          router.navigate(['/auth/login'], {
            queryParams: { returnUrl: router.url },
          });
          break;
        case 403:
          router.navigate(['/forbidden']);
          break;
        case 0:
          console.error('Sem conexão com o servidor. Verifique se o backend está rodando.');
          break;
      }
      return throwError(() => error);
    })
  );
};
