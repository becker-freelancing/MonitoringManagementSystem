import {NgIf} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {ReactiveFormsModule} from "@angular/forms";
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
import {Project} from "../../../../../model/project/project";
import {ProjectManagementService} from "../../../../../services/projectmanagement/projectManagementService";
import {ProjectManagementProject} from "../../projectManagementProject";

@Component({
  selector: 'app-delete-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatFormField, MatInput, NgIf, ReactiveFormsModule],
  templateUrl: './delete-project-dialog.component.html',
  styleUrl: './delete-project-dialog.component.css'
})
export class DeleteProjectDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProjectManagementProject,
    public projectService: ProjectManagementService
  ) {
  }


  ngOnInit(): void {
  }

  deleteProject(): void {
    let project = this.data.project;

    this.projectService.deleteProject(project, (project: Project) => {
      window.location.reload();
    }, (status: number) => {
      alert("Kunde konnte nicht gelöscht werden\n\n" +
        "Error-Code: " + status); //TODO
    });
  }
}
