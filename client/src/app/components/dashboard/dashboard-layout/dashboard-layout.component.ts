import { Component } from '@angular/core';
import * as fa from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterLink, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-dashboard-layout',
  standalone: true,
  imports: [RouterLink, RouterModule, FontAwesomeModule, CommonModule],
  templateUrl: './dashboard-layout.component.html',
  styleUrl: './dashboard-layout.component.scss'
})

export class DashboardLayoutComponent {
  menuItems = [
    {
      name: 'Categories',
      link: 'categories',
      icon: fa.faList,
    },
    {
      name: 'Products',
      link: 'products',
      icon: fa.faShirt,
    },
    {
      name: 'Users',
      link: 'users',
      icon: fa.faUsers,
    },
    {
      name: 'Orders',
      link: 'orders',
      icon: fa.faCubes,
    },
    {
      name: 'Sizes',
      link: 'sizes',
      icon: fa.faRuler,
    },
  ];
}
