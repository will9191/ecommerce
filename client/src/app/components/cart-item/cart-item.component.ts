import { Component, Inject, inject, Input, OnChanges, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent {
  constructor(private cartService: CartService, @Inject(NavbarComponent) private navbar: NavbarComponent) {}

  @Input() cartItems: any;

  removeItem(id: number) {
    this.cartService.removeCartItem(id).subscribe({
      next: (data: any) => {
        console.log(data);
        this.getCartItems();
      },
    });
  }

  closeCart() {
    this.navbar.openCart();
  }

  getCartItems() {
    return this.navbar.getCartItems();
  }
}
