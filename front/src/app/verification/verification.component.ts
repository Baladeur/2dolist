import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from '../services/registration.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { SignUpModalComponent } from '../sign-up-modal/sign-up-modal.component';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss']
})
export class VerificationComponent implements OnInit {
  token!: string | null;
  errorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private registrationService: RegistrationService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.token = params.get('token');
      if (this.token) {
        this.verifyToken();
      }
    });
  }

  verifyToken(): void {
    this.registrationService.verifyRegistrationToken(this.token).subscribe({
      next: (response) => {
        console.log(response);
        this.registrationService.setRegistrationToken(this.token);

        this.openRegistrationFormDialog();
      },
      error: (error) => {
        console.error(error);
        this.errorMessage = 'Token is invalid.';
        this.openSnackBar(this.errorMessage);
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 7000);
      }
    });
  }

  openSnackBar(message: string): void {
    let bar = this.snackBar.open(message, 'OK', {
      duration: 7000,
    });

    bar.onAction().subscribe(() => {
      this.router.navigate(['/']);
    });
  }

  openRegistrationFormDialog(): void {
    const dialogRef = this.dialog.open(SignUpModalComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
