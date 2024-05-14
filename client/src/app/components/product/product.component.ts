import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {
  constructor(private productService: ProductService) {}
  @Input() id: number = 0;

  product: any;

  ngOnInit(): void {
    this.getProductById();
  }

  getProductById() {
    this.productService.getProductById(this.id).subscribe({
      next: (data: any) => {
        this.product = data;
        console.log(data);
      },
    });
  }
}
