import { CommonModule, isPlatformBrowser } from '@angular/common';
import {
  AfterViewInit,
  Component,
  ElementRef,
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
