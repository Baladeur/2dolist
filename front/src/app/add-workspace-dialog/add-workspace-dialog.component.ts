import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-workspace-dialog',
  templateUrl: './add-workspace-dialog.component.html',
  styleUrl: './add-workspace-dialog.component.scss'
})
export class AddWorkspaceDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<AddWorkspaceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close()
  }
}