import { Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { AuthGuard } from './services/auth-guard.service';
import { ProductComponent } from './components/product/product.component';
import { CategoryProductsComponent } from './components/category/category-products/category-products.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { IsAdminGuard } from './services/is-admin-guard.service';
import { DashboardPageComponent } from './pages/dashboard-page/dashboard-page.component';
import { DashboardLayoutComponent } from './components/dashboard/dashboard-layout/dashboard-layout.component';
import { DashboardCategoriesComponent } from './components/dashboard/dashboard-categories/dashboard-categories.component';
import { DashboardProductsComponent } from './components/dashboard/dashboard-products/dashboard-products.component';
import { DashboardUsersComponent } from './components/dashboard/dashboard-users/dashboard-users.component';
import { DashboardOrdersComponent } from './components/dashboard/dashboard-orders/dashboard-orders.component';
import { DashboardSizesComponent } from './components/dashboard/dashboard-sizes/dashboard-sizes.component';

export const routes: Routes = [
  {
    path: '',
    component: NavbarComponent,
    children: [
      {
        path: '',
        component: HomepageComponent,
        title: 'Home',
      },

      {
        path: 'product/:id',
        component: ProductComponent,
        title: 'Product',
        pathMatch: 'full',
      },
      {
        path: ':referenceName/products',
        component: CategoryProductsComponent,
        title: 'Tshirts',
        pathMatch: 'full',
      },
      {
        path: 'user',
        component: UserComponent,
        title: 'User',
        canActivate: [AuthGuard],
      },
    ],
  },
  {
    path: 'admin/dashboard',
    component: DashboardLayoutComponent,
    title: 'Dashboard',
    canActivate: [AuthGuard, IsAdminGuard],
    children: [
      {
        path: 'categories',
        component: DashboardCategoriesComponent,
        title: 'Categories',
      },
      {
        path: 'products',
        component: DashboardProductsComponent,
        title: 'Products',
      },
      {
        path: 'users',
        component: DashboardUsersComponent,
        title: 'Users',
      },
      {
        path: 'orders',
        component: DashboardOrdersComponent,
        title: 'Orders',
      },
      {
        path: 'sizes',
        component: DashboardSizesComponent,
        title: 'Sizes',
      },
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'Login',
  },
  {
    path: 'register',
    component: RegisterComponent,
    title: 'Register',
  },

  {
    path: '**',
    title: 'Not Found',
    redirectTo: '',
  },
];
