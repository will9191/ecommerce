import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-toastr',
  standalone: true,
  imports: [],
  templateUrl: './toastr.component.html',
  styleUrl: './toastr.component.scss',
})
export class ToastrComponent {
  constructor(private toastr: ToastrService) {}

  showError(message: string) {
    this.toastr.error(message, 'Error', {
      timeOut: 5000,
      toastClass: 'ngx-toastr',
      closeButton: false,
      progressBar: true,
      tapToDismiss: false,
    });
  }

  showSuccess(message: string) {
    this.toastr.success(message, 'Success', {
      timeOut: 5000,
      toastClass: 'ngx-toastr',
      closeButton: false,
      progressBar: true,
      tapToDismiss: true,
    });
  }

  showInfo(message: string) {
    this.toastr.info(message, 'Info', {
      timeOut: 5000,
      toastClass: 'ngx-toastr',
      closeButton: false,
      progressBar: true,
      tapToDismiss: true,
    });
  }

  showWarning(message: string) {
    this.toastr.warning(message, 'Warning', {
      timeOut: 5000,
      toastClass: 'ngx-toastr',
      closeButton: false,
      progressBar: true,
      tapToDismiss: false,
    });
  }
}
