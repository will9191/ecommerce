import { Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthComponent } from './components/auth/auth.component';

export const routes: Routes = [
    {
        path: '',
        component: NavbarComponent,
        children: []
    },
    {
        path: 'auth',
        component: AuthComponent,
        title: 'Auth'
    }
];
