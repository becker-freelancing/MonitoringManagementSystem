import {CdkScrollable} from "@angular/cdk/overlay";
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AfterViewInit, Component, EventEmitter, Output} from '@angular/core';
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
import {Project} from "../../../../model/project/project";
import {Updatable} from "../../../../model/util/updatable";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {ProjectManagementProject} from "../projectManagementProject";

@Component({
  selector: 'app-all-projects-table',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    NgOptimizedImage,
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRow,
    MatRow,
    MatHeaderRowDef,
    MatRowDef,
    MatColumnDef,
    CdkScrollable,
    NgClass
  ],
  templateUrl: './all-projects-table.component.html',
  styleUrl: './all-projects-table.component.css'
})
export class AllProjectsTableComponent implements AfterViewInit, Updatable {

  @Output('selectedProject') selectedProject = new EventEmitter<ProjectManagementProject>();
  @Output('dblClickedProject') dblClickedProject = new EventEmitter<ProjectManagementProject>();

  projectService: ProjectManagementService;

  projects: ProjectManagementProject[];
  displayedColumns: string[] = ['id', 'projectName'];

  currentlySelectedProjectUiId: number = -1;
  selectedTableCell: string = 'mat-mdc-row-selected';

  constructor(projectService: ProjectManagementService) {
    this.projects = [];
    this.projectService = projectService;
  }


  ngAfterViewInit(): void {
    this.update();
  }

  update(): void {
    this.projectService.getAllProjects((projects: Project[]): void => {
      this.projects = [];
      let uiId = 0;
      for (let project of projects) {
        uiId++;
        let projectManagementProject = new ProjectManagementProject(uiId, project);
        this.projects.push(projectManagementProject);
      }
    }, (status: number) => alert(status))
  }

  onProjectSingleClicked(project: ProjectManagementProject) {
    this.currentlySelectedProjectUiId = project.uiId;
    this.selectedProject.emit(project);
  }

  emitDblClickedProjectEvent(project: ProjectManagementProject) {
    this.dblClickedProject.emit(project);
  }
}
