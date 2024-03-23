import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import {
  heroShoppingCart,
  heroUser,
  heroInformationCircle,
  heroMagnifyingGlass,
} from '@ng-icons/heroicons/outline';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatIconModule, NgIconComponent],
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
export class NavbarComponent {}
