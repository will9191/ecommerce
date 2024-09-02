import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../services/auth.service';
import { ToastrComponent } from '../toastr/toastr.component';

interface LoginForm {
  email: FormControl;
  password: FormControl;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  providers: [ToastrComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup<LoginForm>;

  constructor(
    private router: Router,
    public authService: AuthService,
    private toastrComponent: ToastrComponent
  ) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    this.authService
      .login(this.loginForm.value.email, this.loginForm.value.password)
      .subscribe({
        next: () => {
          this.toastrComponent.showSuccess('Logged In!'),
            this.router.navigate(['/']);
        },
        error: () => this.toastrComponent.showError('Error on login!'),
      });
  }
}
