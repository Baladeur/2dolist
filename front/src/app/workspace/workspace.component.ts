import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TaskList } from '../models/task-list.model';
import { AddListDialogComponent } from '../add-list-dialog/add-list-dialog.component';
import { ApiService } from '../services/api.service';


@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrl: './workspace.component.scss'
})
export class WorkspaceComponent {

  @Input() workspaceId: number = 0;

  // tasklists to fill from the database, workspace id needs to be inputted
  tasklists: TaskList[] = []

  constructor(public dialog: MatDialog, private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getTaskListsByWorkspaceId(this.workspaceId).subscribe(tasklists => {
      this.tasklists = tasklists
    })
  }

  openAddListDialog(workspaceId: number): void {
    console.log(workspaceId);
    const dialogRef = this.dialog.open(AddListDialogComponent, {
      width: '400px', 
      data: {
        workspaceId: workspaceId 
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      
    })

  }

}
