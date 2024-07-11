import {Component, Inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef
} from "@angular/material/dialog";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-overwrite-document-confirmation-dialog',
  standalone: true,
  imports: [
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    NgForOf
  ],
  templateUrl: './overwrite-document-confirmation-dialog.component.html',
  styleUrl: './overwrite-document-confirmation-dialog.component.css'
})
export class OverwriteDocumentConfirmationDialogComponent {

  documentName: string;

  constructor(public dialogRef: MatDialogRef<OverwriteDocumentConfirmationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data: DialogData) {
    this.documentName = data.documentName;
  }

  onDiscard() {
    this.dialogRef.close(false);
  }

  onSave() {
    this.dialogRef.close(true);
  }
}

interface DialogData {
  documentName: string;
}
