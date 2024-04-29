import {Injectable} from "@angular/core";
import {Customer} from "../../model/cutomer/customer";
import {Project} from "../../model/project/project";
import {HttpClient} from "../http/httpClient";

@Injectable({
  providedIn: 'root',
})
export class ProjectManagementService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  closeProjectsForCustomer(customer: Customer, onSuccess: (projects: Project[]) => void, onError: (status: number) => void) : void {

  }

  deleteProject(project: Project, onSuccess: (project: Project) => void, onError?: (status: number) => void) {
    if(project.id == undefined){
      return;
    }
    this.httpClient.delete('projects/delete', project.id).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToProject(r.data))
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  saveProject(project: Project, onSuccess?: (project: Project) => void, onError?: (status: number) => void) {
    this.httpClient.post('projects/save', project).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      if (onSuccess) {
        onSuccess(this.mapToProject(r.data))
      }
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  getAllProjects(onSuccess: (projects: Project[]) => void, onError: (status: number) => void) {
    this.httpClient.get('projects/get').then(r => {
      if (r.status != 200){
        onError(r.status);
        return;
      }

      let responseProjects: Project[] = [];

      for (let dataItem of r.data){
        responseProjects.push(this.mapToProject(dataItem));
      }
      onSuccess(responseProjects)
    }).catch((reason: any) => {
      onError(reason.status)
    })
  }

  mapToProject(data: ProjectResponseData): Project {

    return new Project(
      data.title,
      data.creationTime,
      data.projectId,
      data.shortDescription === null ? undefined : data.shortDescription,
      data.longDescription === null ? undefined : data.longDescription,
      data.startTime === null ? undefined : data.startTime,
      data.endTime === null ? undefined : data.endTime,
      data.customerId === null ? undefined : data.customerId
    )
  }
}

interface ProjectResponseData {

  projectId: number;
  title: string;
  shortDescription: string | null;
  longDescription: string | null;
  creationTime: Date;
  startTime: Date | null;
  endTime: Date | null;
  customerId: number | null;
}
