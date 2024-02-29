import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../services/api.service';


@Component({
  selector: 'app-delete-list-dialog',
  templateUrl: './delete-list-dialog.component.html',
  styleUrl: './delete-list-dialog.component.scss'
})
export class DeleteListDialogComponent {
  listId: number = 0;
  isDelete: string = 'false';

  constructor(
    public dialogRef: MatDialogRef<DeleteListDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.listId = data.listId;
  }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    if (this.isDelete == 'true')
      this.apiService.deleteTaskList(this.listId).subscribe();
    this.onNoClick();
  }

}
