import { Component, OnInit } from '@angular/core';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss',
})
export class OrdersComponent implements OnInit {
  constructor(private ordersService: OrdersService) {}

  orders: any;

  ngOnInit(): void {
    this.getUserOrders();
  }

  getUserOrders() {
    this.ordersService.getUserOrders().subscribe({
      next: (data: any) => {
        this.orders = data;
        console.log(data);
      },
    });
  }
}
