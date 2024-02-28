import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Workspace } from '../models/workspace.model';
import { AddWorkspaceDialogComponent } from '../add-workspace-dialog/add-workspace-dialog.component';
import { EditWorkspaceDialogComponent } from '../edit-workspace-dialog/edit-workspace-dialog.component';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})

export class SidebarComponent {
  workspaces: Workspace[] = []

  constructor(public dialog: MatDialog, private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getWorkspaces().subscribe(workspaces => {
      this.workspaces = workspaces
    })
  }

  openAddWorkspaceDialog(): void {
    const dialogRef = this.dialog.open(AddWorkspaceDialogComponent, {
      width: '400px'
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }

  openEditWorkspaceDialog(workspaceId: number): void {
    const dialogRef = this.dialog.open(EditWorkspaceDialogComponent, {
      width: '400px',
      data: {
        workspaceId: workspaceId,
        workspaceName: this.workspaces[workspaceId].name,
        workspaceDesc: this.workspaces[workspaceId].description,
        workspaceColor: this.workspaces[workspaceId].color
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }
}
