import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getProfile(): Observable<any>{
    return this.httpClient.get<any>(`${apiEndpoint.UserEndpoint.profile}`)
  }
}
