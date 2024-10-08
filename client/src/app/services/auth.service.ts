import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginResponse } from '../types/login-response.type';
import { isPlatformBrowser } from '@angular/common';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.httpClient
      .post<LoginResponse>(`${apiEndpoint.AuthEndpoint.authenticate}`, {
        email,
        password,
      })
      .pipe(
        tap((value) => {
          localStorage.setItem('access_token', value.access_token);
          localStorage.setItem('refresh_token', value.refresh_token);

          localStorage.setItem(
            'is_admin',
            JSON.stringify(value.is_admin|| false)
          );
        })
      );
  }

  register(
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    role: string
  ) {
    return this.httpClient
      .post<LoginResponse>(`${apiEndpoint.AuthEndpoint.register}`, {
        firstName,
        lastName,
        email,
        password,
        role
      })
      .pipe(
        tap((value) => {
          localStorage.setItem('access_token', value.access_token);
          localStorage.setItem('refresh_token', value.refresh_token);
          localStorage.setItem(
            'is_admin',
            JSON.stringify(value.is_admin || false)
          );
        })
      );
  }

  logout(): Observable<any> {
    return this.httpClient.get<any>(`${apiEndpoint.AuthEndpoint.logout}`);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('access_token');
  }

  isAdmin(): boolean {
    return JSON.parse(localStorage.getItem('is_admin') || 'false');
  }

  getAuthToken(): string | null {
    return localStorage.getItem('access_token');
  }
}
