import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskList } from '../models/task-list.model';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-add-list-dialog',
  templateUrl: './add-list-dialog.component.html',
  styleUrl: './add-list-dialog.component.scss'
})
export class AddListDialogComponent {
  list: TaskList = {
    id: 0,
    name: '',
    color: '',
    description: '',
    workspaceId: 0
  };

  constructor(
    public dialogRef: MatDialogRef<AddListDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private apiService: ApiService
  ) {
    this.list.workspaceId = data.workspaceId;
    }

  onNoClick(): void {
    this.dialogRef.close()
  }

  onSubmit(): void {
    this.apiService.createTaskList(this.list).subscribe();
    this.onNoClick();
  }

}
