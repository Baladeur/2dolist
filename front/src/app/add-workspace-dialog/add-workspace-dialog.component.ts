import { Component} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Workspace } from '../models/workspace.model';
import { WorkspaceVisibility } from '../models/enums/workspace-visibility.enum';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-add-workspace-dialog',
  templateUrl: './add-workspace-dialog.component.html',
  styleUrls: ['./add-workspace-dialog.component.scss']
})
export class AddWorkspaceDialogComponent {
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
    public dialogRef: MatDialogRef<AddWorkspaceDialogComponent>,
    private apiService: ApiService
  ) {}

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    if (this.isPrivate != 'true')
      this.workspace.visibility = WorkspaceVisibility.PUBLIC;
    this.apiService.createWorkspace(this.workspace).subscribe();
    this.onNoClick();
  }
}