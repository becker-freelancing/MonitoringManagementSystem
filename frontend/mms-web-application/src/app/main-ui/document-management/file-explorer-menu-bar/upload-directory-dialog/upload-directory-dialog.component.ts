import {Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";
import {JsonPipe, NgForOf} from "@angular/common";
import {File} from "node:buffer";
import {FileType} from "../../../../../model/files/fileType";

@Component({
  selector: 'app-upload-directory-dialog',
  standalone: true,
  imports: [
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    NgForOf,
    JsonPipe
  ],
  templateUrl: './upload-directory-dialog.component.html',
  styleUrl: './upload-directory-dialog.component.css'
})
export class UploadDirectoryDialogComponent {

  files: any[] = []

  constructor(public dialogRef: MatDialogRef<UploadDirectoryDialogComponent>) {
  }

  onSave() {
    let documents: CreateDocumentResultData[] = [];
    for (const file of this.files) {
      let split = file.name.split(".");
      documents.push({
        file: file,
        documentName: split[0],
        fileType: new FileType(split[1])
      });
    }
    this.dialogRef.close(documents);
  }

  onDirectoryUpload(event: any) {
    this.files = Array.from(event.target.files);
  }

}

export interface CreateDocumentResultData {
  file: File;
  documentName: string;
  fileType: FileType;
}
