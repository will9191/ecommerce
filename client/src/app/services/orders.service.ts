import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoint } from '../constants/constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrdersService {
  constructor(private httpClient: HttpClient) {}

  getUserOrders(): Observable<any> {
    return this.httpClient.get<any>(`${apiEndpoint.OrdersEndpoint.userOrders}`);
  }

  getAll(): Observable<any> {
    return this.httpClient.get<any>(`${apiEndpoint.OrdersEndpoint.base}`);
  }


  saveOrder(cartItemsId: number[]): Observable<any> {
    return this.httpClient.post<any>(`${apiEndpoint.OrdersEndpoint.base}`, {cartItemsId});
  }

  getOrderById(id: number): Observable<any> {
    return this.httpClient.get<any>(
      `${apiEndpoint.OrdersEndpoint.base}/${id}`
    );
  }
}
