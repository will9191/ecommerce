import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private httpClient: HttpClient) {}

  getProductById(id: number): Observable<any> {
    return this.httpClient.get<any>(
      `${apiEndpoint.ProductEndpoint.getById}/${id}`
    );
  }

  getAllProducts(): Observable<any> {
    return this.httpClient.get<any>(`${apiEndpoint.ProductEndpoint.getById}`);
  }
}
