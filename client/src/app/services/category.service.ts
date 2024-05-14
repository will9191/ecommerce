import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoint } from '../constants/constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private httpClient: HttpClient) {}

  getProductsByCategory(referenceName: string): Observable<any> {
    return this.httpClient.get<any>(
      `${apiEndpoint.CategoryEndpoint.getByReferenceName}/${referenceName}`
    );
  }

  getAll(): Observable<any> {
    return this.httpClient.get<any>(`${apiEndpoint.CategoryEndpoint.base}`);
  }
}
