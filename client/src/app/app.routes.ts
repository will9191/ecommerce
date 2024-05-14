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

export const routes: Routes = [
  {
    path: '',
    component: NavbarComponent,
    children: [
      {
        path: '',
        component: HomepageComponent,
       title: 'Home'
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
