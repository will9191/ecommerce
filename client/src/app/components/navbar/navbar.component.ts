import { CommonModule, isPlatformBrowser } from '@angular/common';
import {
  Component,
  Inject,
  OnChanges,
  OnInit,
  PLATFORM_ID,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, RouterModule } from '@angular/router';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import {
  heroShoppingCart,
  heroUser,
  heroInformationCircle,
  heroMagnifyingGlass,
} from '@ng-icons/heroicons/outline';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MatIconModule,
    NgIconComponent,
    RouterLink,
    CommonModule,
    RouterModule,
  ],
  viewProviders: [
    provideIcons({
      heroShoppingCart,
      heroUser,
      heroInformationCircle,
      heroMagnifyingGlass,
    }),
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent implements OnInit {
  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private categoryService: CategoryService
  ) {}
  isLoggedIn: any;
  categories: any;

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.isLoggedIn = localStorage.getItem('access_token');
    }
    this.getCategories();
  }

  getCategories() {
    this.categoryService.getAll().subscribe({
      next: (data: any) => {
        this.categories = data;
      },
    });
  }
}
