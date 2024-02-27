import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-task-dialog',
  templateUrl: './edit-task-dialog.component.html',
  styleUrl: './edit-task-dialog.component.scss'
})
export class EditTaskDialogComponent {
  name: string = '';
  dateStart: string = '';
  dateEnd: string = '';
  color: string = '';
  description: string = '';
  id: number = 0;

  constructor(
    public dialogRef: MatDialogRef<EditTaskDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.name = data.taskName;
    this.description = data.taskDesc;
    this.color = data.taskColor;
    this.id = data.taskId;
    this.dateStart = data.taskStart;
    this.dateEnd = data.taskEnd;
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.onNoClick();
  }

}
