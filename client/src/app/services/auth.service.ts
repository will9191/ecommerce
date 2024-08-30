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
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  private isAdminSubject = new BehaviorSubject<boolean>(false);
  private accessTokenSubject = new BehaviorSubject<string | null>(null);
  private refreshTokenSubject = new BehaviorSubject<string | null>(null);

  isLoggedIn$ = this.isLoggedInSubject.asObservable();
  isAdmin$ = this.isAdminSubject.asObservable();
  accessToken$ = this.accessTokenSubject.asObservable();
  refreshToken$ = this.refreshTokenSubject.asObservable();

  constructor(private httpClient: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.httpClient
      .post<LoginResponse>(`${apiEndpoint.AuthEndpoint.authenticate}`, {
        email,
        password,
      })
      .pipe(
        tap((value) => {
          this.accessTokenSubject.next(value.access_token);
          this.refreshTokenSubject.next(value.refresh_token);
          this.isLoggedInSubject.next(true);
          this.isAdminSubject.next(value.isAdmin || false);
          console.log(
            this.isLoggedIn$.subscribe((isLogged) => {
              console.log(isLogged);
            })
          );
          console.log(
            this.accessToken$.subscribe((isLogged) => {
              console.log(isLogged);
            })
          );
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
          this.accessTokenSubject.next(value.access_token);
          this.refreshTokenSubject.next(value.refresh_token);
          this.isLoggedInSubject.next(true);
          this.isAdminSubject.next(value.isAdmin || false);
        })
      );
  }

  logout(): void {
    this.httpClient.get<any>(`${apiEndpoint.AuthEndpoint.logout}`).subscribe({
      next: () => {
        this.accessTokenSubject.next(null);
        this.refreshTokenSubject.next(null);
        this.isLoggedInSubject.next(false);
        this.isAdminSubject.next(false);
      },
    });
  }

  isLoggedIn(): boolean {
    return this.isLoggedInSubject.value;
  }

  isAdmin(): boolean {
    return this.isAdminSubject.value;
  }

  getAuthToken(): string | null {
    return this.accessTokenSubject.value;
  }
}
