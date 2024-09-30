import { Component, Inject, inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CartService } from '../../services/cart.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ToastrComponent } from '../toastr/toastr.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule],
  providers: [ToastrComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private router: Router,
    private authService: AuthService,
    private toastrComponent: ToastrComponent
  ) {}

  @Input() id: number = 0;

  sizeName!: any;
  quantity = 1;
  size!: any;

  product: any;

  data: CartInterface = {
    productId: this.id,
    size: {
      name: this.sizeName,
      quantity: this.quantity,
    },
  };

  ngOnInit(): void {
    this.getProductById();
    console.log(this.size);
  }

  click(sizeName: any) {
    this.sizeName = sizeName;
    this.data.productId = this.id;
    this.data.size.name = sizeName;
  }

  submit() {
    if (this.authService.isLoggedIn()) {
      if (this.sizeName) {
        console.log(this.id);
        console.log(this.size);
        console.log(this.quantity);
        console.log(this.data);
        this.cartService.addToCart(this.data).subscribe({
          next: (data:any) => {this.toastrComponent.showSuccess('Successfully added!'), this.product = data.body.product},
          error: (data: any) => {
            this.toastrComponent.showError(data.error.message),
              console.log(data.error.message);
          },
        });
      } else {
        this.toastrComponent.showWarning('You should add one size!');
      }
    } else {
      this.router.navigate(['login']);
    }
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

export interface CartInterface {
  productId: number;
  size: {
    name: string;
    quantity: number;
  };
}
