import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Workspace } from '../models/workspace.model';
import { WorkspaceVisibility } from '../models/enums/workspace-visibility.enum';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-edit-workspace-dialog',
  templateUrl: './edit-workspace-dialog.component.html',
  styleUrl: './edit-workspace-dialog.component.scss'
})
export class EditWorkspaceDialogComponent {
  workspace: Workspace = {
    id: 0,
    name: '',
    color: '',
    background: '',
    description: '',
    visibility: WorkspaceVisibility.PRIVATE,
    userIds: []
  };
  isPrivate: string = 'true';

  constructor(
    public dialogRef: MatDialogRef<EditWorkspaceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.workspace = data.workspace
    this.isPrivate = this.workspace.visibility == WorkspaceVisibility.PRIVATE? 'true' : 'false'
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.workspace.visibility = this.isPrivate != 'true'? WorkspaceVisibility.PUBLIC : WorkspaceVisibility.PRIVATE ;
    this.apiService.updateWorkspace(this.workspace.id, this.workspace).subscribe();
    this.onNoClick();
  }

}
