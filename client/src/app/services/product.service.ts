import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private httpClient: HttpClient) {}

  getProductById(id: number) {
    return this.httpClient.get<any>(
      `${apiEndpoint.ProductEndpoint.getById}/${id}`
    );
  }

  getAllProducts() {
    return this.httpClient.get<any>(`${apiEndpoint.ProductEndpoint.getById}`);
  }
}
