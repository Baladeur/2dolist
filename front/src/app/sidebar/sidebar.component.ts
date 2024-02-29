import { Component, EventEmitter, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Workspace } from '../models/workspace.model';
import { AddWorkspaceDialogComponent } from '../add-workspace-dialog/add-workspace-dialog.component';
import { EditWorkspaceDialogComponent } from '../edit-workspace-dialog/edit-workspace-dialog.component';
import { DeleteWorkspaceDialogComponent } from '../delete-workspace-dialog/delete-workspace-dialog.component';
import { ApiService } from '../services/api.service';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})

export class SidebarComponent {
  workspaces: Workspace[] = []

  @Output() workspaceId = new EventEmitter<number>();

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
    })

  }

  selectWorkspace(workspace: Workspace): void {
    this.workspaceId.emit(workspace.id);
  }

  openEditWorkspaceDialog(workspace: Workspace): void {
    const dialogRef = this.dialog.open(EditWorkspaceDialogComponent, {
      width: '400px',
      data: {
        workspace: workspace
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      
    })

  }

  openDeleteWorkspaceDialog(workspace: Workspace): void {
    const dialogRef = this.dialog.open(DeleteWorkspaceDialogComponent, {
      width: '400px',
      data: {
        workspaceId: workspace.id
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      
    })

  }
}
