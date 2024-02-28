import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginModalComponent } from '../login-modal/login-modal.component';
import { __values } from 'tslib';
import { RegisterEmailComponent } from '../register-email/register-email.component';
import { RegisterEmail } from '../models/dto/RegisterEmail';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  isLoggedIn: boolean = false;

  constructor(
    public dialog: MatDialog,
    private authService: AuthService) {
      this.authService.isLoggedIn().subscribe((value) => {
        this.isLoggedIn = value;
      })
    }

  openSignUpDialog(): void {
    const dialogRef = this.dialog.open(RegisterEmailComponent, {
      width: '450px',
      height: '450px',
      data: new RegisterEmail('', '', null),
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('Formulaire fermé');
    })
  }

  openLoginDialog(): void {
    const dialogRef = this.dialog.open(LoginModalComponent, {
      width: '350px',
      height: '400px',
      data: {},
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log(result)
      console.log('Fenêtre de connexion fermer')
    })
  }

  logout(): void {
    this.authService.logout();
  }
}
