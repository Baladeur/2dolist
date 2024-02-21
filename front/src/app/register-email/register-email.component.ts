import { Component, Inject } from '@angular/core';
// import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { RegisterEmail } from '../models/dto/RegisterEmail';
import { RegistrationService } from './../services/registration.service';


@Component({
  selector: 'app-register-email',
  templateUrl: './register-email.component.html',
  styleUrl: './register-email.component.scss'
})
export class RegisterEmailComponent {
  email!: string;
  emailRepeat!: string;

  constructor(
    public dialogRef: MatDialogRef<RegisterEmailComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: RegisterEmail,
    public registrationService: RegistrationService
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }


  onSubmit() {
    this.onNoClick();
    this.registrationService.registerEmail(
      new RegisterEmail(this.email, this.emailRepeat, null)).subscribe(
        res => {
          console.log(res);
        }
    );
  }

}
