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

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent implements OnInit {
  constructor(
    private cartService: CartService,
    private ref: MatDialogRef<CartItemComponent>
  ) {
    ref.backdropClick().subscribe(() => {
      ref.close();
    });
  }

  cart: any;

  ngOnInit(): void {
    this.getCart();
  }

  removeItem(id: number) {
    this.cartService.removeCartItem(id).subscribe({
      next: (data: any) => {
        console.log(data);
        this.getCart();
      },
    });
  }

  removeQuantity(id: number) {
    this.cartService.removeQuantity(id).subscribe({
      next: (data: any) => {
        console.log(data);
        this.getCart();
      },
    });
  }

  addQuantity(id: number) {
    this.cartService.addQuantity(id).subscribe({
      next: (data: any) => {
        console.log(data);
        this.getCart();
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

  closeCart() {
    this.ref.close();
  }

  trackById(index: number, cart: any): number {
    return cart.cartItems.cartItemId;
  }
}
