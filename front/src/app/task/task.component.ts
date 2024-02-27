import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditTaskDialogComponent } from '../edit-task-dialog/edit-task-dialog.component';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() taskId: number = 0;
  @Input() taskName: string = '';
  @Input() taskDesc: string = '';
  @Input() taskColor: string = '';
  @Input() taskStart: string = '';
  @Input() taskEnd: string = '';

  constructor(public dialog: MatDialog) {}

  openEditTaskDialog(): void {
    const dialogRef = this.dialog.open(EditTaskDialogComponent, {
      width: '400px',
      data: {
        taskId: this.taskId,
        taskName: this.taskName,
        taskDesc: this.taskDesc,
        taskColor: this.taskColor,
        taskStart: this.taskStart,
        taskEnd: this.taskEnd
      }
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log('the dialog was closed');
    })

  }
}
