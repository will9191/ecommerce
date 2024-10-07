import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private profileSubject = new BehaviorSubject<any>(null);
  profile$ = this.profileSubject.asObservable();
  private profileCoinsSubject = new BehaviorSubject<any>(null);
  profileCoins$ = this.profileCoinsSubject.asObservable();

  constructor(private httpClient: HttpClient) {
    this.loadProfile();
  }

  loadProfile() {
    return this.httpClient
      .get<any>(`${apiEndpoint.UserEndpoint.profile}`)
      .subscribe({
        next: (data: any) => {
          this.updateProfile(data);
        },
      });
  }

  addCoins(quantity: number) {
    return this.httpClient.post<any>(
      `${apiEndpoint.UserEndpoint.addCoins}`,
      quantity
    );
  }

  getProfile() {
    return this.profile$;
  }

  getCoins() {
    return this.profileCoins$;
  }

  updateProfile(data: any) {
    this.profileSubject.next(data);
    this.profileCoinsSubject.next(data.coins);
    console.log(data.coins);
  }
}
