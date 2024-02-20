import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { RegistrationService } from '../services/registration.service';
import { RegisterPassword } from '../models/dto/RegisterPassword';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sign-up-modal',
  templateUrl: './sign-up-modal.component.html',
  styleUrl: './sign-up-modal.component.scss'
})
export class SignUpModalComponent implements OnInit {
  signUpForm!: FormGroup;
  
  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<SignUpModalComponent>,
    private formBuilder: FormBuilder,
    private registrationService: RegistrationService,
    private snackBar: MatSnackBar,
    private router: Router
    ) {}
  ngOnInit(){
    this.initForm();
  }

  initForm() {
    this.signUpForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required],
    }, { validator: this.checkPasswords });
  }

  checkPasswords(group: FormGroup) {
    const pass = group.get('password')?.value;
    const confirmPass = group.get('confirmPassword')?.value;
    return pass === confirmPass ? null : { notSame: true }
  }

  onSubmit() {
    if(this.signUpForm.valid) {
      console.log('Inscription:', this.signUpForm.value)
      let { email, password, confirmPassword } = this.signUpForm.value;

      this.onCloseClick();

      this.registrationService.registerEmailAndPassword(
        new RegisterPassword(email, password, confirmPassword, this.registrationService.getRegistrationToken())).subscribe(
          res => {
            console.log(res);
            this.showSnackBarAndOpenHome('Registration completed successfully');
          },
          error => {
            console.error(error);
            this.showSnackBarAndOpenHome(error!.error!.message);
          }
      );
    } else {
      console.log('Formulaire non valide')
    }
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

  showSnackBarAndOpenHome(message: string): void {
    let snackBarRef = this.snackBar.open(message, 'Fermer', {
      duration: 5000,
    });

    snackBarRef.afterDismissed().subscribe(() => {
      this.router.navigate(['/']);
    });

    setTimeout(() => {
      this.router.navigate(['/']);
    }, 5000);
  }
}