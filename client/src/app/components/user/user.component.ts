import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { RouterModule } from '@angular/router';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss',
})
export class UserComponent implements OnInit {
  constructor(
    private userService: UserService,
    private orderService: OrdersService
  ) {}

  profile: any;
  orders: any;

  ngOnInit(): void {
    this.getProfile();
    this.getOrders();
  }

  getProfile() {
    this.userService.getProfile().subscribe({
      next: (data: any) => {
        this.profile = data;
      },
      error: (any) => {
        console.log('error');
      },
    });
  }

  getOrders() {
    this.orderService.getUserOrders().subscribe({
      next: (data: any) => {
        this.orders = data;
      },
      error: (any) => {
        console.log('error');
      },
    });
  }
}
