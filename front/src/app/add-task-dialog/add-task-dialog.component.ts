import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-task-dialog',
  templateUrl: './add-task-dialog.component.html',
  styleUrl: './add-task-dialog.component.scss'
})
export class AddTaskDialogComponent {
  name: string = '';
  dateStart: Date = new Date();
  dateEnd: Date = new Date();
  color: string = '';
  description: string = '';

  constructor(
    public dialogRef: MatDialogRef<AddTaskDialogComponent>
  ) {}

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.onNoClick();
  }

}
