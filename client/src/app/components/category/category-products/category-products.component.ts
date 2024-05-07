import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryService } from 'src/app/services/category.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-category-products',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-products.component.html',
  styleUrl: './category-products.component.css',
})
export class CategoryProductsComponent implements OnInit {
  constructor(private categoryService: CategoryService) {}

  @Input()
  referenceName: string = '';

  products: any;

  ngOnInit(): void {
    this.getProductsByCategory();
  }

  getProductsByCategory() {
    this.categoryService.getProductsByCategory(this.referenceName).subscribe({
      next: (data: any) => {
        this.products = data.products;
        console.log(this.products);
        console.log(data);
      },
    });
  }
}
