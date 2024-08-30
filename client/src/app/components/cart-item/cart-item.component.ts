import {
  Component,
  Inject,
  inject,
  Input,
  OnChanges,
  OnInit,
} from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatDialogRef } from '@angular/material/dialog';
import { OrdersService } from '../../services/orders.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent implements OnInit {
  constructor(
    public cartService: CartService,
    private orderService: OrdersService,
    private ref: MatDialogRef<CartItemComponent>,
    private toastrService: ToastrService
  ) {
    ref.backdropClick().subscribe(() => {
      ref.close();
    });
  }

  cart: any = this.cartService.cart$;

  ngOnInit(): void {
    this.getCart();
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

  getCart() {
    this.cartService.getCart().subscribe({
      next: (data: any) => {
        this.cart = data;
        console.log(data);
      },
    });
  }

  saveOrder() {
    const cartItemsId = this.cart.cartItems.map((item: any) => item.id);

    this.orderService.saveOrder(cartItemsId).subscribe({
      next: () => this.cartService.loadCart(),
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
