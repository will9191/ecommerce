import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';

import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  accessToken: any;

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true; // Permite acesso à rota
    } else {
      this.router.navigate(['/login']); // Redireciona para a página de login
      return false; // Bloqueia acesso à rota
    }
  }
}
