import { Component } from '@angular/core';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-dashboard-categories',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-categories.component.html',
  styleUrl: './dashboard-categories.component.scss'
})
export class DashboardCategoriesComponent {
  constructor(private categoryService: CategoryService) {}

  categories: any = [];

  ngOnInit(): void {
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
