import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskList } from '../models/task-list.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-edit-list-dialog',
  templateUrl: './edit-list-dialog.component.html',
  styleUrl: './edit-list-dialog.component.scss'
})
export class EditListDialogComponent {
  list: TaskList = {
    id: 0,
    name: '',
    color: '',
    description: '',
    workspaceId: 0
  };

  constructor(
    public dialogRef: MatDialogRef<EditListDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.list = data.list;
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.apiService.updateTaskList(this.list.id, this.list).subscribe();
    this.onNoClick();
  }

}
