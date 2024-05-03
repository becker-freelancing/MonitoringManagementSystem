import {Component, ViewChild} from '@angular/core';
import {AllProjectsTableComponent} from "./all-projects-table/all-projects-table.component";
import {ProjectManagementProject} from "./projectManagementProject";
import {ProjectManagementMenuBarComponent} from "./projects-management-menu-bar/project-management-menu-bar.component";

@Component({
  selector: 'app-project-management',
  standalone: true,
  imports: [
    ProjectManagementMenuBarComponent,
    AllProjectsTableComponent
  ],
  templateUrl: './project-management.component.html',
  styleUrl: './project-management.component.css'
})
export class ProjectManagementComponent {

  @ViewChild('projectManagementMenuBar') menuBar?: ProjectManagementMenuBarComponent;

  currentlySelectedProject: ProjectManagementProject | null = null;

  onDoubleClickedProject(project: ProjectManagementProject) {
    this.currentlySelectedProject = project;
    this.menuBar?.openEditProjectDialog(project);
  }

  changeSelectedProjectProperty(project: ProjectManagementProject) {
    this.currentlySelectedProject = project;
  }
}
