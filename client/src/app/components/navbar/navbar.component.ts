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
import { ToastrService } from 'ngx-toastr';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AuthService } from '../../services/auth.service';

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
    MatDialogModule,
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
    private categoryService: CategoryService,
    private cartService: CartService,
    private toastrService: ToastrService,
    private router: Router,
    private matDialog: MatDialog,
    public authService: AuthService
  ) {}

  categories: any;
  isOpen: boolean = false;
  menuOpen: boolean = false;

  cart: any;
  cartLength: any = 0;

  ngOnInit(): void {
    this.getCategories();
    this.getCart();
    this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
      console.log('Is logged in:', isLoggedIn);
    });
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
    if (this.authService.getAuthToken() != null) {
      this.matDialog.open(CartItemComponent, { disableClose: true });
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
    if (this.authService.getAuthToken()) {
      this.menuOpen = !this.menuOpen;
    } else {
      this.router.navigate(['/login']);
    }
  }

  // logout() {
  //   this.authService.logout().subscribe({
  //     next: () => {
  //       this.toastrService.success('Logged Out!');
  //       this.router.navigate(['/login']);
  //     },
  //   });
  // }
  logout() {}
}
