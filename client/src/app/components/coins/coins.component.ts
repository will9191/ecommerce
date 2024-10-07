import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ToastrComponent } from '../toastr/toastr.component';

@Component({
  selector: 'app-coins',
  standalone: true,
  imports: [],
  providers: [ToastrComponent],
  templateUrl: './coins.component.html',
  styleUrl: './coins.component.scss',
})
export class CoinsComponent {
  constructor(
    private userService: UserService,
    private toastrComponent: ToastrComponent
  ) {}
  data = [
    {
      id: 1,
      quantity: 300,
    },
    {
      id: 2,
      quantity: 600,
    },
    {
      id: 3,
      quantity: 900,
    },
  ];

  addCoins(quantity: number) {
    this.userService.addCoins(quantity).subscribe({
      next: (any) => {
        this.toastrComponent.showSuccess(`Added ${quantity} coins`);
        this.userService.loadProfile();
      },
    });
  }
}
