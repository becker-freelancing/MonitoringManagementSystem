import {Component, Inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {ConfirmDialogData} from "./confirm-dialog.service";

@Component({
  selector: 'app-confirm-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatDialogTitle,
    MatDialogActions,
    MatButton
  ],
  templateUrl: './confirm-dialog.component.html',
  styleUrl: './confirm-dialog.component.css'
})
export class ConfirmDialogComponent {
  content: string;
  title: string;

  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data: ConfirmDialogData) {
    this.content = data.content;
    this.title = data.title;
  }

  cancel() {
      this.dialogRef.close(false);
  }

  confirm() {
    this.dialogRef.close(true);
  }
}
