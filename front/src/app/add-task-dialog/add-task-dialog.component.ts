import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Task } from '../models/task.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-add-task-dialog',
  templateUrl: './add-task-dialog.component.html',
  styleUrl: './add-task-dialog.component.scss'
})
export class AddTaskDialogComponent {
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
    public dialogRef: MatDialogRef<AddTaskDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.task.taskListId = data.list.id;
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.task.start = new Date(this.dateStart);
    this.task.end = new Date(this.dateEnd);
    this.apiService.createTask(this.task).subscribe();
    this.onNoClick();
  }

}
