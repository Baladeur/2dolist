import { Component } from '@angular/core';
import { SignUpModalComponent } from '../sign-up-modal/sign-up-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { LoginModalComponent } from '../login-modal/login-modal.component';
// import { AuthService } from '../auth/auth.service';
import { __values } from 'tslib';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  
  isLoggedIn: boolean = false;

  constructor(
    public dialog: MatDialog) {}
    
    /*private authService: AuthService) {
      this.authService.isloggedIn.subscribe((value) => {
        this.isLoggedIn = value;
      })
    }*/

  openSignUpDialog(): void {
    const dialogRef = this.dialog.open(SignUpModalComponent, {
      width: '450px',
      height: '450px',
      data: {},
      
    
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
      console.log('Fenêtre de connexion fermer')
    })
  }
}
