import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-workspace-dialog',
  templateUrl: './edit-workspace-dialog.component.html',
  styleUrl: './edit-workspace-dialog.component.scss'
})
export class EditWorkspaceDialogComponent {
  name: string = '';
  color: string = '';
  description: string = ''
  id: number = 0;

  constructor(
    public dialogRef: MatDialogRef<EditWorkspaceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.name = data.workspaceName;
    this.color = data.workspaceColor;
    this.description = data.workspaceDesc;
    this.id = data.workspaceId;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.onNoClick();
  }

}
