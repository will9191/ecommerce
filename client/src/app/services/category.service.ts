import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private httpClient: HttpClient) {}

  getProductsByCategory(referenceName: string) {
    return this.httpClient.get<any>(
      `${apiEndpoint.CategoryEndpoint.getByReferenceName}/${referenceName}`
    );
  }

  getAll() {
    return this.httpClient.get<any>(`${apiEndpoint.CategoryEndpoint.base}`);
  }
}
