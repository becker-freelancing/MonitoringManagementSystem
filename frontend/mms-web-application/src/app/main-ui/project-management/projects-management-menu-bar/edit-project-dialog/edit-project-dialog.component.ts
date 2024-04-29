import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatTab, MatTabContent, MatTabGroup} from "@angular/material/tabs";
import {MatTooltip} from "@angular/material/tooltip";
import {DeepCloneService} from "../../../../../services/util/deepCloneService";
import {ConfirmDialogService} from "../../../../util/confirm-dialog/confirm-dialog.service";
import {ProjectManagementProject} from "../../projectManagementProject";


@Component({
  selector: 'app-edit-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatTabGroup, MatTab, MatFormField, MatInput, ReactiveFormsModule, MatTabContent, MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable, NgOptimizedImage, MatHeaderCellDef, NgIf, NgClass, NgForOf, MatSelect, MatOption, FormsModule, MatTooltip],
  templateUrl: './edit-project-dialog.component.html',
  styleUrl: './edit-project-dialog.component.css'
})
export class EditProjectDialogComponent implements OnInit {

  project: ProjectManagementProject;

  editProjectDataForm: FormGroup;
  projectNameNotValid: boolean = false;

  constructor(public dialogRef: MatDialogRef<EditProjectDialogComponent, ProjectManagementProject>,
              public confirmDialogService: ConfirmDialogService,
              editProjectDataFormBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) data: ProjectManagementProject) {

    this.project = new DeepCloneService().deepCopy(data);

    this.editProjectDataForm = editProjectDataFormBuilder.group({
      title: [this.project.project.title, Validators.required],
    })
  }

  ngOnInit(): void {
  }

  close() {
    this.confirmDialogService.showConfirmCancelDialog(() => this.dialogRef.close(), () => {});
  }

  save() {
    if (!this.editProjectDataForm.valid) {
      this.projectNameNotValid = this.editProjectDataForm.value.title === undefined;
      return;
    }

    let editProjectDataValues = this.editProjectDataForm.value;

    this.project.project.title = editProjectDataValues.title;

    this.dialogRef.close(this.project)
  }

}
