import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Task } from '../models/task.model';
import { AddTaskDialogComponent } from '../add-task-dialog/add-task-dialog.component';
import { EditListDialogComponent } from '../edit-list-dialog/edit-list-dialog.component';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss'
})
export class TaskListComponent {

  // Get the list id from  the worspace component
  @Input() listId: number = 0;
  @Input() listName: string = "";
  @Input() color: string = "";
  @Input() description: string = "";

  // tasks to fill from the database, tasklist id inputted from the workspace component
  tasks: Task[] = [
    { id: 0, name: "Serenity", color: "#90d4e3", description: "Lorem ipsum dolor sit amet.", dateStart: "2021-09-01", dateEnd: "2021-05-30" },
    { id: 1, name: "Impulse", color: "#f95959", description: "Nunc interdum mauris non diam tincidunt ullamcorper.", dateStart: "2022-02-10", dateEnd: "2022-02-17" },
    { id: 2, name: "Harmony", color: "#fac05e", description: "Donec placerat nibh euismod sapien.", dateStart: "2021-06-09", dateEnd: "2021-09-09" },
    { id: 3, name: "Cadence", color: "#81b214", description: "In hac habitasse platea dictumst.", dateStart: "2022-04-10", dateEnd: "2022-09-10" },
    { id: 4, name: "Genesis", color: "#faebd7", description: "Morbi euismod laoreet odio eu consequat.", dateStart: "2022-03-03", dateEnd: "2022-07-03" },
    { id: 5, name: "Horizon", color: "#4dbbd5", description: "Nunc aliquam elit at magna sollicitudin tincidunt.", dateStart: "2021-12-03", dateEnd: "2021-12-05" },
    { id: 6, name: "Vigilant", color: "#cc2936", description: "Etiam vehicula mauris quis placerat.", dateStart: "2022-02-24", dateEnd: "2022-08-24" },
    { id: 7, name: "Echo", color: "#b497d6", description: "Proin in sapien congue, cursus sem nec, iaculis tellus.", dateStart: "2022-04-07", dateEnd: "2023-01-07" },
    { id: 8, name: "Catalyst", color: "#ffbb33", description: "Nunc non laoreet dui. Phasellus et tellus vel magna fermentum lacinia.", dateStart: "2021-05-20", dateEnd: "2022-01-20" },
    { id: 9, name: "Nexus", color: "#59abe3", description: "Donec at tellus quis urna cursus laoreet.", dateStart: "2022-01-09", dateEnd: "2022-12-09" }
  ]
  

  constructor(public dialog: MatDialog) {}

  openAddTaskDialog(): void {
    const dialogRef = this.dialog.open(AddTaskDialogComponent, {
      width: '400px'
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }

  openEditListDialog(): void {
    const dialogRef = this.dialog.open(EditListDialogComponent, {
      width: '400px',
      data: {
        listId: this.listId,
        listName: this.listName,
        listDesc: this.description,
        listColor: this.color
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }

  
}
