import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoint } from '../constants/constants';
import { tap } from 'rxjs';
import { LoginResponse } from '../types/login-response.type';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string) {
    return this.httpClient.post<LoginResponse>(
      `${apiEndpoint.AuthEndpoint.authenticate}`, {email, password}
    ).pipe(
      tap((value) => {
        sessionStorage.setItem('access_token', value.access_token),
        sessionStorage.setItem('refresh_token', value.refresh_token)
      })
    )
  }
}
