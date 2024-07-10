import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef
} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-delete-directory-dialog',
  standalone: true,
  imports: [
    MatDialogActions,
    MatDialogContent,
    MatDialogClose,
    MatButton
  ],
  templateUrl: './delete-directory-dialog.component.html',
  styleUrl: './delete-directory-dialog.component.css'
})
export class DeleteDirectoryDialogComponent {

  directory: string;

  constructor(
    public dialogRef: MatDialogRef<DeleteDirectoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: DialogData
  ) {
    this.directory = data.dir;
  }

  onSave() {
    this.dialogRef.close(true);
  }

  onCancel() {
    this.dialogRef.close(false);
  }
}

interface DialogData {
  dir: string;
}
