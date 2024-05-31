import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private httpClient: HttpClient) {}

  getCart(): Observable<any> {
    return this.httpClient.get<any>(
      `${apiEndpoint.CartEndpoint.base}`
    );
  }

  removeCartItem(id: number): Observable<any> {
    return this.httpClient.delete<any>(
      `${apiEndpoint.CartEndpoint.remove}/${id}`
    );
  }

  addToCart(data:any):Observable<any> {
    return this.httpClient.post<any>(
      `${apiEndpoint.CartEndpoint.add}`, data
    )
  }
}
