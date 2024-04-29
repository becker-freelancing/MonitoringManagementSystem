import {NgClass} from "@angular/common";
import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {Project} from "../../../../model/project/project";
import {Updatable} from "../../../../model/util/updatable";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {ProjectManagementProject} from "../projectManagementProject";
import {AddProjectDialogComponent} from "./add-project-dialog/add-project-dialog.component";
import {DeleteProjectDialogComponent} from "./delete-project-dialog/delete-project-dialog.component";
import {EditProjectDialogComponent} from "./edit-project-dialog/edit-project-dialog.component";

@Component({
  selector: 'app-project-management-menu-bar',
  standalone: true,
  imports: [MatButtonModule, NgClass],
  templateUrl: './project-management-menu-bar.component.html',
  styleUrl: './project-management-menu-bar.component.css'
})
export class ProjectManagementMenuBarComponent implements Updatable, OnChanges{

  @Input('currentlySelectedProject') currentlySelectedProject: ProjectManagementProject | null = null;

  projectManagementService: ProjectManagementService;
  editButtonClass: string = 'disabled-button';

  constructor(
    public dialog: MatDialog,
    projectManagementService: ProjectManagementService) {
    this.projectManagementService = projectManagementService;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["currentlySelectedProject"]){
      let newProject = changes['currentlySelectedProject'].currentValue;
      if(newProject != null){
        this.editButtonClass = 'secondary-button';
      }
    }
  }

  openAddProjectDialog(): void {
    let dialogRef = this.dialog.open(AddProjectDialogComponent, {
      width: '400px',
      height: '300px'
    });

    dialogRef.afterClosed().subscribe(project => {
      this.projectManagementService.saveProject(project);
      this.update();
    })

  }

  openEditProjectDialog(project: ProjectManagementProject | null) {
    if (project == null){
      return;
    }

    this.dialog.open(EditProjectDialogComponent, {
      width: '90%',
      height: '90%',
      data: project
    }).afterClosed().subscribe(project => {
      this.projectManagementService.saveProject(project.project,
        (project: Project) => this.update());
    });
  }

  openDeleteProjectDialog(project: ProjectManagementProject | null) {
    if(project == null){
      return;
    }

    this.dialog.open(DeleteProjectDialogComponent, {
      data: project
    });
  }

  update(): void {
    window.location.reload();
  }


}
