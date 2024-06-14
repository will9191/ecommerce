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
import { Router, RouterLink, RouterModule } from '@angular/router';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import {
  matShoppingCartOutline,
  matPersonOutline,
  matInfoOutline,
  matSearchOutline,
  matDashboardOutline,
  matLogoutOutline,
  matSettingsOutline,
} from '@ng-icons/material-icons/outline';
import { CategoryService } from '../../services/category.service';
import { CartService } from '../../services/cart.service';
import { CartItemComponent } from '../cart-item/cart-item.component';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';

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
      matLogoutOutline,
      matSettingsOutline,
    }),
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent implements OnInit {
  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private categoryService: CategoryService,
    private cartService: CartService,
    private loginService: LoginService,
    private toastrService: ToastrService,
    private router: Router
  ) {}

  isLoggedIn: any;
  categories: any;
  isOpen: boolean = false;
  menuOpen: boolean = false;

  cart: any;
  cartLength: any = 0;

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.isLoggedIn = localStorage.getItem('access_token');
    }
    this.getCategories();
    this.getCart();
  }

  getCart() {
    this.cartService.getCart().subscribe({
      next: (data: any) => {
        this.cart = data;
        this.cartLength = data.cartItems.length;
        console.log(data);
      },
    });
  }

  openCart() {
    if (this.loginService.getAuthToken()) {
      this.isOpen = !this.isOpen;
    } else {
      this.router.navigate(['/login']);
    }
  }

  getCategories() {
    this.categoryService.getAll().subscribe({
      next: (data: any) => {
        this.categories = data;
      },
    });
  }

  setMenuOpen() {
    if (this.loginService.getAuthToken()) {
      this.menuOpen = !this.menuOpen;
    } else {
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.loginService.logout().subscribe({
      next: () => {
        this.toastrService.success('Logged Out!');
        this.router.navigate(['/login']);
      },
    });
  }
}
