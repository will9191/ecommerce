import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private httpClient: HttpClient) {}

  getCartItems(): Observable<any> {
    return this.httpClient.get<any>(
      `${apiEndpoint.CartEndpoint.base}/cartItems`
    );
  }

  removeCartItem(id: number): Observable<any> {
    return this.httpClient.delete<any>(
      `${apiEndpoint.CartEndpoint.remove}/${id}`
    );
  }
}
