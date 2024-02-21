import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-sign-up-modal',
  templateUrl: './sign-up-modal.component.html',
  styleUrl: './sign-up-modal.component.scss'
})
export class SignUpModalComponent implements OnInit {
  signUpForm!: FormGroup;
  
  constructor(public dialog: MatDialog, private formBuilder: FormBuilder) {}
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
    } else {
      console.log('Formulaire non valide')
    }
  }
}