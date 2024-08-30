import { Component, Input, OnInit } from '@angular/core';
import { OrdersService } from '../../services/orders.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.scss',
})
export class OrderDetailsComponent implements OnInit {
  constructor(private orderService: OrdersService) {}
  @Input() id: number = 0;
  order: any;

  ngOnInit(): void {
    this.getOrderById();
  }

  getOrderById() {
    this.orderService.getOrderById(this.id).subscribe({
      next: (data: any) => {
        this.order = data;
        console.log(data);
      },
    });
  }
}
