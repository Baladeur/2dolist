import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditTaskDialogComponent } from '../edit-task-dialog/edit-task-dialog.component';
import { DeleteTaskDialogComponent } from '../delete-task-dialog/delete-task-dialog.component';
import { Task } from '../models/task.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() task: Task = {
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

  constructor(public dialog: MatDialog) {}

  openEditTaskDialog(): void {
    const dialogRef = this.dialog.open(EditTaskDialogComponent, {
      width: '400px',
      data: {
        task: this.task
      }
    })

    dialogRef.afterClosed().subscribe(result => {

    })

  }

  openDeleteTaskDialog(): void {
    const dialogRef = this.dialog.open(DeleteTaskDialogComponent, {
      width: '400px',
      data: {
        task: this.task
      }
    })

    dialogRef.afterClosed().subscribe(result => {

    })

  }
}
