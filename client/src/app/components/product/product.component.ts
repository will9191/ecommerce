import { Component, Inject, inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CartService } from '../../services/cart.service';
import { ToastrService } from 'ngx-toastr';
import { NavbarComponent } from '../navbar/navbar.component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private toastrService: ToastrService,
    private router: Router,
    @Inject(NavbarComponent) private navbar: NavbarComponent,
    @Inject(LoginService) private auth: LoginService
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
    console.log(this.sizeName);
    this.data.productId = this.id;
    this.data.size.name = sizeName;
    return console.log(this.id);
  }

  submit() {
    const authToken = this.auth.getAuthToken();
    if (authToken) {
      if (this.sizeName) {
        console.log(this.id);
        console.log(this.size);
        console.log(this.quantity);
        console.log(this.data);
        this.cartService.addToCart(this.data).subscribe({
          next: () => this.toastrService.success('Successfully added!'),
          error: () => this.router.navigate(['']),
        });
        window.location.reload();
      } else {
        this.toastrService.error('You should add one size!');
      }
    } else {
      this.router.navigate(['/login']);
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
