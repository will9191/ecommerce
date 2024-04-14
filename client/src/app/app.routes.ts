import { Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { AuthGuard } from './services/auth-guard.service';

export const routes: Routes = [
    {
        path: '',
        component: NavbarComponent,
        children: []
    },
    {
        path: 'login',
        component: LoginComponent,
        title: 'Login'
    },
    {
        path: 'register',
        component: RegisterComponent,
        title: 'Register'
    },
    {
        path: 'user',
        component: UserComponent,
        title: 'User',
        canActivate: [AuthGuard]
    }
];
