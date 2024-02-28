import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-list-dialog',
  templateUrl: './add-list-dialog.component.html',
  styleUrl: './add-list-dialog.component.scss'
})
export class AddListDialogComponent {
  name: string = '';
  color: string = '';
  description: string = '';

  constructor(
    public dialogRef: MatDialogRef<AddListDialogComponent>
  ) {}

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.onNoClick();
  }

}
