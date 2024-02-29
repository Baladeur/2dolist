import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-delete-workspace-dialog',
  templateUrl: './delete-workspace-dialog.component.html',
  styleUrl: './delete-workspace-dialog.component.scss'
})
export class DeleteWorkspaceDialogComponent {
  workspaceId: number = 0;
  isDelete: string = 'false';

  constructor(
    public dialogRef: MatDialogRef<DeleteWorkspaceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.workspaceId = data.workspaceId;
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    if (this.isDelete == 'true')
      this.apiService.deleteWorkspace(this.workspaceId).subscribe();
    this.onNoClick();
  }
}
