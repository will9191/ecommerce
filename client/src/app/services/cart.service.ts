import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartSubject = new BehaviorSubject<any>(null); // Armazena o estado do carrinho
  cart$ = this.cartSubject.asObservable();
  private cartItemsSubject = new BehaviorSubject<any>(null); // Armazena o estado do carrinho
  cartItemsLength$ = this.cartItemsSubject.asObservable();

  constructor(private httpClient: HttpClient) {
    this.loadCart();
  }

  loadCart() {
    return this.httpClient
      .get<any>(`${apiEndpoint.CartEndpoint.base}`)
      .subscribe({
        next: (data: any) => {
          this.updateCart(data);
        },
      });
  }

  getCart() {
    return this.cart$;
  }

  addToCart(data: any): Observable<any> {
    return this.httpClient
      .post<any>(`${apiEndpoint.CartEndpoint.add}`, data)
      .pipe(tap((data: any) => this.updateCart(data.body.cart)));
  }

  updateCart(data: any) {
    this.cartSubject.next(data);
    this.cartItemsSubject.next(data.cartItems.length);
    console.log(data.cartItems.length);
  }

  removeCartItem(id: number): Observable<any> {
    return this.httpClient
      .delete<any>(`${apiEndpoint.CartEndpoint.remove}/${id}`)
      .pipe(tap((data: any) => this.updateCart(data.body)));
  }

  addQuantity(id: number): Observable<any> {
    return this.httpClient
      .post<any>(`${apiEndpoint.CartEndpoint.addQuantity}`, id)
      .pipe(tap((data: any) => this.updateCart(data.body)));
  }

  removeQuantity(id: number): Observable<any> {
    return this.httpClient
      .post<any>(`${apiEndpoint.CartEndpoint.removeQuantity}`, id)
      .pipe(tap((data: any) => this.updateCart(data.body)));
  }
}
