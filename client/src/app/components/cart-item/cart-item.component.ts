import {
  Component,
  Inject,
  inject,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatDialogRef } from '@angular/material/dialog';
import { OrdersService } from '../../services/orders.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ToastrComponent } from '../toastr/toastr.component';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule],
  providers: [ToastrComponent],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent implements OnInit {
  constructor(
    public cartService: CartService,
    private orderService: OrdersService,
    private ref: MatDialogRef<CartItemComponent>,
    private toastrService: ToastrService,
    private router: Router,
    private toastrComponent: ToastrComponent
  ) {
    ref.backdropClick().subscribe(() => {
      ref.close();
    });
  }

  cart: any;
  order: any;

  ngOnInit(): void {
    this.getCart();
  }

  getCart() {
    return this.cartService.getCart().subscribe({
      next: (data: any) => {
        this.cart = data;
      },
    });
  }

  removeItem(id: number) {
    this.cartService.removeCartItem(id).subscribe({
      next: (data: any) => {
        this.cart = data.body;
      },
    });
  }

  removeQuantity(id: number) {
    this.cartService.removeQuantity(id).subscribe({
      next: (data: any) => {
        this.cart = data.body;
      },
    });
  }

  addQuantity(id: number) {
    this.cartService.addQuantity(id).subscribe({
      next: (data: any) => {
        this.cart = data.body;
      },
    });
  }

  saveOrder() {
    const cartItemsId = this.cart.cartItems.map((item: any) => item.id);
    this.orderService.saveOrder(cartItemsId).subscribe({
      next: (data: any) => {
        this.order = data.body;
        this.cartService.loadCart();
        this.closeCart();
        this.toastrComponent.showSuccess('Order created!');
        this.router.navigate([`user/orders/${this.order.id}`]);
      },
      error: () => this.toastrService.error('Error on ordering!'),
    });
  }

  closeCart() {
    this.ref.close();
  }

  trackById(index: number, cart: any): number {
    return cart.cartItems.cartItemId;
  }
}
