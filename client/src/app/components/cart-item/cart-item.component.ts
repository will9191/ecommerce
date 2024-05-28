import { Component, Input, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent implements OnInit {
  constructor(private cartService: CartService) {}

  @Input() cartItems: any;

  removeItem(id:number){
    this.cartService.removeCartItem(id).subscribe({});
  }

  ngOnInit(): void {

  }
}
