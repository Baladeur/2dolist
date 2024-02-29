import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Task } from '../models/task.model';
import { ApiService } from '../services/api.service';

function pad(num: number): string {
  let num_str: string = "0" + num;
  return num_str.substring(num_str.length - 2);
}

@Component({
  selector: 'app-edit-task-dialog',
  templateUrl: './edit-task-dialog.component.html',
  styleUrl: './edit-task-dialog.component.scss'
})

export class EditTaskDialogComponent {
  task: Task = {
    id: -1,
    name : '',
    shortName: '',
    color: '',
    priorityColor: '',
    description: '',
    taskListId: -1,
    ownerId: -1,
    dateLastActivity: new Date(),
    start: new Date(),
    end: new Date(),
    closed: false
  };
  dateStart: string = '';
  dateEnd: string = '';

  constructor(
    public dialogRef: MatDialogRef<EditTaskDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.task = data.task;
    this.dateStart = data.task.start.getFullYear() + "-" + pad(data.task.start.getMonth() + 1) + "-" + pad(data.task.start.getDate());
    this.dateEnd = data.task.end.getFullYear() + "-" + pad(data.task.end.getMonth() + 1) + "-" + pad(data.task.end.getDate());
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.task.start = new Date(this.dateStart);
    this.task.end = new Date(this.dateEnd);
    this.apiService.updateTask(this.task.id, this.task).subscribe();
    this.onNoClick();
  }

}
