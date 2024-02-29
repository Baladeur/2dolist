import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../services/api.service';


@Component({
  selector: 'app-delete-task-dialog',
  templateUrl: './delete-task-dialog.component.html',
  styleUrl: './delete-task-dialog.component.scss'
})
export class DeleteTaskDialogComponent {
  taskId: number = 0;
  isDelete: string = 'false';

  constructor(
    public dialogRef: MatDialogRef<DeleteTaskDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.taskId = data.taskId;
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    if (this.isDelete == 'true')
      this.apiService.deleteTask(this.taskId).subscribe();
    this.onNoClick();
  }

}
