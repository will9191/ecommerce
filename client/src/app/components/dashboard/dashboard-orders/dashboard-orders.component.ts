import { Component } from '@angular/core';
import { OrdersService } from '../../../services/orders.service';

@Component({
  selector: 'app-dashboard-orders',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-orders.component.html',
  styleUrl: './dashboard-orders.component.scss',
})
export class DashboardOrdersComponent {
  constructor(private orderService: OrdersService) {}

  orders: any ;

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders() {
    this.orderService.getAll().subscribe({
      next: (data: any) => {
        this.orders = data;
        console.log(this.orders)
      },
    });
  }
}
