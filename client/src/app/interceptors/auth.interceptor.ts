import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from '../services/login.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = inject(LoginService).getAuthToken();
  const cloneRequest = req.clone({
    setHeaders: {
      Authorization: `Bearer ${authToken}`,
    },
  });
  return next(cloneRequest);
};
