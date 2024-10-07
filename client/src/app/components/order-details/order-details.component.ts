import { Component, Input, OnInit } from '@angular/core';
import { OrdersService } from '../../services/orders.service';
import { RouterModule } from '@angular/router';
import { ToastrComponent } from '../toastr/toastr.component';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [RouterModule],
  providers: [ToastrComponent],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.scss',
})
export class OrderDetailsComponent implements OnInit {
  constructor(
    private orderService: OrdersService,
    private toastrComponent: ToastrComponent,
    private userService: UserService
  ) {}
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

  pay() {
    const orderId = this.order.id;
    this.orderService.pay(orderId).subscribe({
      next: (data: any) => {
        this.toastrComponent.showSuccess('Successfully Paid!'),
          this.userService.loadProfile();
        this.order = data.body;
      },

      error: (data: any) => {
        this.toastrComponent.showError(data.error.message),
          console.log(data.error.message);
      },
    });
  }
}
