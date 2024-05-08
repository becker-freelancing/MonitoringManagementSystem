import {NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose, MatDialogContainer,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {Project} from "../../../../../model/project/project";
import {DateTime} from "../../../../../model/util/DateTime";
import {ProjectManagementService} from "../../../../../services/projectmanagement/projectManagementService";

@Component({
  selector: 'app-add-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, NgForOf, FormsModule, MatDialogContainer, MatFormField, MatInput, ReactiveFormsModule, NgIf, MatRadioGroup, MatRadioButton],
  templateUrl: './add-project-dialog.component.html',
  styleUrl: './add-project-dialog.component.css',
  providers: [ProjectManagementService]
})
export class AddProjectDialogComponent implements OnInit{

  @Output('savedProject') savedProjectOutput = new EventEmitter<Project>();

  form: FormGroup;

  projectName: string = '';

  projectNameNotValid: boolean = false;


  constructor(
    fb: FormBuilder,
    private dialogRef: MatDialogRef<AddProjectDialogComponent>) {
    this.form = fb.group({
      projectName: [this.projectName, Validators.required]
    });
  }

  ngOnInit() {

  }

  saveProject() {
    if(this.form.valid) {
      let formValues = this.form.value;

      let project = new Project(formValues['projectName'], new DateTime());
      this.dialogRef.close(project);
    } else {
      this.projectNameNotValid = true;
    }
  }
}
