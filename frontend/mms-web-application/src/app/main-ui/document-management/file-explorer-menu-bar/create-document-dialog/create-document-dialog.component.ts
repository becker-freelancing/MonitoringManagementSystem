import { Component } from '@angular/core';
import {Form, FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {FileType} from "../../../../../model/files/fileType";
import {MatButton} from "@angular/material/button";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {File} from "node:buffer";

@Component({
  selector: 'app-create-document-dialog',
  standalone: true,
  imports: [
    FormsModule,
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatFormField,
    MatInput,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './create-document-dialog.component.html',
  styleUrl: './create-document-dialog.component.css'
})
export class CreateDocumentDialogComponent {

  documentName?: string;
  fileType?: FileType;
  file?: File;

  constructor(
              public dialogRef: MatDialogRef<CreateDocumentDialogComponent>){

  }

  onFileUpload(event: any){
    let files = event.target.files;
    let file = files[0];

    if(!file){
      return;
    }

    let name = file.name as string;

    let nameSplit = name.split("\.");

    this.documentName = nameSplit[0];
    this.fileType = FileType.fromFileEnding(nameSplit[1]);
    this.file = file;
  }

  onSave() {
    this.dialogRef.close({
      file: this.file,
      documentName: this.documentName,
      fileType: this.fileType
    })
  }

  onDocumentNameChange($event: Event) {
    this.documentName = ($event.target as HTMLInputElement).value;
  }

}

export interface CreateDocumentResultData{
  file: File;
  documentName: string;
  fileType: FileType;
}
