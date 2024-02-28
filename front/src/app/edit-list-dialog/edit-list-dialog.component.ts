import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-list-dialog',
  templateUrl: './edit-list-dialog.component.html',
  styleUrl: './edit-list-dialog.component.scss'
})
export class EditListDialogComponent {
  name: string = '';
  color: string = '';
  description: string = '';
  id: number = 0;


  constructor(
    public dialogRef: MatDialogRef<EditListDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.name = data.listName;
    this.id = data.listId;
    this.color = data.listColor;
    this.description = data.listDesc;
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.onNoClick();
  }

}
