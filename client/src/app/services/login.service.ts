import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { apiEndpoint } from '../constants/constants';
import { tap } from 'rxjs';
import { LoginResponse } from '../types/login-response.type';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(
    private httpClient: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  login(email: string, password: string) {
    return this.httpClient
      .post<LoginResponse>(`${apiEndpoint.AuthEndpoint.authenticate}`, {
        email,
        password,
      })
      .pipe(
        tap((value) => {
          if (isPlatformBrowser(this.platformId)) {
            localStorage.setItem('access_token', value.access_token),
              localStorage.setItem('refresh_token', value.refresh_token);
            this.getAuthToken();
          }
        })
      );
  }

  register(
    firstName: string,
    lastName: string,
    email: string,
    password: string
  ) {
    return this.httpClient
      .post<LoginResponse>(`${apiEndpoint.AuthEndpoint.register}`, {
        firstName,
        lastName,
        email,
        password,
      })
      .pipe(
        tap((value) => {
          if (isPlatformBrowser(this.platformId)) {
            localStorage.setItem('access_token', value.access_token),
              localStorage.setItem('refresh_token', value.refresh_token);
            this.getAuthToken();
          }
        })
      );
  }

  getAuthToken(): any {
    if (isPlatformBrowser(this.platformId)) {
      console.log(localStorage.getItem('access_token'));
      return localStorage.getItem('access_token');
    }
  }

  logout(): any {
    return this.httpClient
      .get<any>(`${apiEndpoint.AuthEndpoint.logout}`)
      .subscribe({
        next: (data: any) => {
          localStorage.setItem('access_token', ''),
            localStorage.setItem('refresh_token', '');
        },
      });
  }
}
