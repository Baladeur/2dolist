import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../services/auth.service';
import { Authenticate } from '../models/dto/Authenticate';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrl: './login-modal.component.scss'
})
export class LoginModalComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(
    public dialogRef: MatDialogRef<LoginModalComponent>,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.onNoClick();
    this.authService.login(new Authenticate(this.email, this.password)).subscribe({
      next: response => {
        console.log('Login success:', response);
      },
      error: error => {
        console.error('Login error:', error!.error!.message);
      }
    });
  }
}