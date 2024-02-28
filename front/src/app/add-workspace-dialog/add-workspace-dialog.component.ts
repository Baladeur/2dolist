import { Component} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-workspace-dialog',
  templateUrl: './add-workspace-dialog.component.html',
  styleUrls: ['./add-workspace-dialog.component.scss']
})
export class AddWorkspaceDialogComponent {
  name: string = '';
  color: string = '';
  isPrivate: boolean = true;
  description: string = '';

  constructor(
    public dialogRef: MatDialogRef<AddWorkspaceDialogComponent>
  ) {}

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.onNoClick();
  }
}