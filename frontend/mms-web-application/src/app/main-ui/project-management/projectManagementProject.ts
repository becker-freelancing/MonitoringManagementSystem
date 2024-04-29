import {Project} from "../../../model/project/project";

export class ProjectManagementProject{

  uiId: number;
  project: Project;


  constructor(uiId: number, project: Project) {
    this.uiId = uiId;
    this.project = project;
  }
}
