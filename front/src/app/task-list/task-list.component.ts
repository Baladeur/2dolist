import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Task } from '../models/task.model';
import { AddTaskDialogComponent } from '../add-task-dialog/add-task-dialog.component';
import { EditListDialogComponent } from '../edit-list-dialog/edit-list-dialog.component';
import { DeleteListDialogComponent } from '../delete-list-dialog/delete-list-dialog.component';
import { TaskList } from '../models/task-list.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss'
})
export class TaskListComponent {

  // Get the list id from  the worspace component
  @Input() list: TaskList = {
    id: 0,
    name: '',
    color: '',
    description: '',
    workspaceId: 0
  };

  // tasks to fill from the database, tasklist id inputted from the workspace component
  tasks: Task[] = []
  

  constructor(public dialog: MatDialog, private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getTasksByTaskListId(this.list.id).subscribe(tasks => {
      this.tasks = tasks
    })
  }

  openAddTaskDialog(): void {
    const dialogRef = this.dialog.open(AddTaskDialogComponent, {
      width: '400px',
      data: {
        list: this.list
      }
    })

    dialogRef.afterClosed().subscribe(result => {

    })

  }

  openEditListDialog(): void {
    const dialogRef = this.dialog.open(EditListDialogComponent, {
      width: '400px',
      data: {
        list: this.list
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      
    })

  }

  openDeleteListDialog(): void {
    const dialogRef = this.dialog.open(DeleteListDialogComponent, {
      width: '400px',
      data: {
        list: this.list
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      
    })

  }

  
}
