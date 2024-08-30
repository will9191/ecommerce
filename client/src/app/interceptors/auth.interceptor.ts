import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = inject(AuthService).getAuthToken();
  if (authToken) {
    const cloneRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authToken}`,
      },
    });
    return next(cloneRequest);
  } else {
    const cloneRequest = req.clone({
      setHeaders: {
        Authorization: '',
      },
    });
    return next(cloneRequest);
  }
};
