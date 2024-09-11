import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-dashboard-products',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-products.component.html',
  styleUrl: './dashboard-products.component.scss',
})
export class DashboardProductsComponent implements OnInit {
  constructor(private productService: ProductService) {}

  products: any = [];

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.productService.getAllProducts().subscribe({
      next: (data: any) => {
        this.products = data;
      },
    });
  }
}
