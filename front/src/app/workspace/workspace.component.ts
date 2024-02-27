import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Tasklist } from '../models/tasklist.model';
import { AddListDialogComponent } from '../add-list-dialog/add-list-dialog.component';


@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrl: './workspace.component.scss'
})
export class WorkspaceComponent {

  // tasklists to fill from the database, workspace id needs to be inputted
  tasklists: Tasklist[] = [
    { id: 0, name: 'Focus', color: '#f1f1f1', description: "Stay on track" },
    { id: 1, name: 'Groceries', color: '#d4d4d4', description: "Stock up" },
    { id: 2, name: 'Errands', color: '#cccccc', description: "To-do list" },
    { id: 3, name: 'Workout', color: '#c2c2c2', description: "Get active" },
    { id: 4, name: 'Reading', color: '#a9a9a9', description: "Dive into a book" },
    { id: 5, name: 'Planning', color: '#909090', description: "Map it out" },
    { id: 6, name: 'Brainstorm', color: '#777777', description: "Spark ideas" },
    { id: 7, name: 'Deadlines', color: '#5e5e5e', description: "Meet the targets" },
    { id: 8, name: 'Finances', color: '#454545', description: "Manage the budget" },
    { id: 9, name: 'Relaxation', color: '#2c2c2c', description: "Unwind and recharge" }
  ]

  constructor(public dialog: MatDialog) {}

  openAddListDialog(): void {
    const dialogRef = this.dialog.open(AddListDialogComponent, {
      width: '400px'
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }

}
