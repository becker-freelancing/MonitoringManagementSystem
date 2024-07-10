import {Component, Inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef
} from "@angular/material/dialog";
import {DocumentWithoutContent} from "../../../../../model/documents/DocumentWithoutContent";
import {JsonPipe} from "@angular/common";

@Component({
  selector: 'app-delete-document-dialog',
  standalone: true,
  imports: [
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    JsonPipe
  ],
  templateUrl: './delete-document-dialog.component.html',
  styleUrl: './delete-document-dialog.component.css'
})
export class DeleteDocumentDialogComponent {

  document: DocumentWithoutContent

  constructor(
    public dialogRef: MatDialogRef<DeleteDocumentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data: DialogData
  ) {
    console.log(data.document)
    this.document = data.document;
  }

  onSave() {
    this.dialogRef.close(true);
  }

  onCancel() {
    this.dialogRef.close(false);
  }
}

interface DialogData {
  document: DocumentWithoutContent
}
