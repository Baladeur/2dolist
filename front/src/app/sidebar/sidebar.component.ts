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
  workspaces: Workspace[] = [
    { id: 0, name: 'Discovery', color: '#80BFFF', description: "Explore the unknown" },
    { id: 1, name: 'Inspiration', color: '#FFB6C1', description: "Spark creativity" },
    { id: 2, name: 'Gratitude', color: '#F4A460', description: "Appreciate the good" },
    { id: 3, name: 'Growth', color: '#90EE90', description: "Develop and improve" },
    { id: 4, name: 'Connection', color: '#FFDAB9', description: "Build relationships" },
    { id: 5, name: 'Adventure', color: '#00CED1', description: "Embrace new experiences" },
    { id: 6, name: 'Mindfulness', color: '#8A2BE2', description: "Be present in the moment" },
    { id: 7, name: 'Learning', color: '#FF69B4', description: "Expand your knowledge" },
    { id: 8, name: 'Contribution', color: '#FFD700', description: "Make a positive impact" },
    { id: 9, name: 'Wellbeing', color: '#7CFC00', description: "Nurture your mind and body" }
  ]

  constructor(public dialog: MatDialog) {}

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
