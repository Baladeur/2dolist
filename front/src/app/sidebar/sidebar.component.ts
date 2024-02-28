import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Workspace } from '../models/workspace.model';
import { AddWorkspaceDialogComponent } from '../add-workspace-dialog/add-workspace-dialog.component';
import { ApiService } from '../services/api.service';


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
      width: '250px'
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog wwas closed');
    })

  }
}
