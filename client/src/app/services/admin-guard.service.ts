import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root',
})
export class AdminGuardService implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  canActivate(): boolean {
    if (this.authService.isLoggedIn() && this.authService.isAdmin()) {
      return true; // Permite acesso
    } else {
      this.router.navigate(['/no-access']); // Redireciona se não for admin
      return false; // Bloqueia acesso
    }
  }
}
