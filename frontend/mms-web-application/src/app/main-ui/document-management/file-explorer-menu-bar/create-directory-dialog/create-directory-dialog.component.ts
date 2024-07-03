import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-create-directory-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    ReactiveFormsModule,
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatFormField,
    MatInput,
    NgIf
  ],
  templateUrl: './create-directory-dialog.component.html',
  styleUrl: './create-directory-dialog.component.css'
})
export class CreateDirectoryDialogComponent {

  form: FormGroup;

  directoryName: string = '';
  directoryNameNotValid = false;
  directoryNameWrongRegex = false;

  constructor(fb: FormBuilder,
              private dialogRef: MatDialogRef<CreateDirectoryDialogComponent>) {

    this.form = fb.group({
      directoryName: [this.directoryName, Validators.required]
    })
  }

  createDirectory() {
    if(this.form.valid){
      let dirname = this.form.value['directoryName'] as string;

      if(dirname.length == 0){
        this.directoryNameNotValid = true;
        return;
      }

      if(dirname.indexOf("\\") === -1){
        this.dialogRef.close(dirname);
      } else {
        this.directoryNameWrongRegex = true;
        this.directoryNameNotValid = false;
      }
    } else {
      this.directoryNameWrongRegex = false;
      this.directoryNameNotValid = true;
    }
  }
}
