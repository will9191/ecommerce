import { CommonModule, isPlatformBrowser } from '@angular/common';
import {
  AfterViewInit,
  Component,
  ElementRef,
  inject,
  Inject,
  OnInit,
  PLATFORM_ID,
  ViewChild,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, RouterModule } from '@angular/router';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import {
  matShoppingCartOutline,
  matPersonOutline,
  matInfoOutline,
  matSearchOutline,
  matDashboardOutline,
} from '@ng-icons/material-icons/outline';
import { CategoryService } from '../../services/category.service';
import { CartService } from '../../services/cart.service';
import { CartItemComponent } from '../cart-item/cart-item.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MatIconModule,
    NgIconComponent,
    RouterLink,
    CommonModule,
    RouterModule,
    CartItemComponent,
  ],
  viewProviders: [
    provideIcons({
      matShoppingCartOutline,
      matPersonOutline,
      matInfoOutline,
      matSearchOutline,
      matDashboardOutline,
    }),
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent implements OnInit {
  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private categoryService: CategoryService,
    private cartService: CartService
  ) {}

  isLoggedIn: any;
  categories: any;
  isOpen: boolean = false;

  cartItems: any;
  cartLength: any = 0;

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.isLoggedIn = localStorage.getItem('access_token');
    }
    this.getCategories();
    this.getCartItems();
  }

  getCartItems() {
    this.cartService.getCartItems().subscribe({
      next: (data: any) => {
        this.cartItems = data;
        this.cartLength = data.length;
        console.log(data);
      },
    });
  }

  openCart() {
    this.isOpen = !this.isOpen;
  }

  getCategories() {
    this.categoryService.getAll().subscribe({
      next: (data: any) => {
        this.categories = data;
      },
    });
  }
}
