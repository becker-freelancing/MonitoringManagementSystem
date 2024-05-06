import {Injectable} from "@angular/core";
import {CustomerManagementCustomer} from "../../model/customerManagementCustomer";
import {Customer} from "../../model/cutomer/customer";
import {Project} from "../../model/project/project";
import {CustomerManagementService} from "../customermanagement/customerManagementService";
import {HttpClient} from "../http/httpClient";
import {HttpServiceCache} from "../http/httpServiceCache";

@Injectable({
  providedIn: 'root',
})
export class ProjectManagementService {

  httpClient: HttpClient;
  cache: HttpServiceCache<Project>;

  constructor() {
    this.httpClient = new HttpClient();
    this.cache = HttpServiceCache.getInstance(Project);
  }

  getAllCustomersWithOpenProjects(onSuccess: (customers: CustomerManagementCustomer[]) => void, onError?: (status: number) => void){
    this.getAllProjects((projects: Project[]) => {
      let openProjects = projects.filter(project => project.isActive());
      let customerManagementService = new CustomerManagementService();
      let customers: CustomerManagementCustomer[] = [];

      let uiId = 1;
      for (let project of openProjects){
        customerManagementService.getCustomer(project.customerId ?? -1, (customer: Customer) => {
          let customerManagementCustomer = new CustomerManagementCustomer(uiId, customer);
          customerManagementCustomer.projects = [project];
          customers.push(customerManagementCustomer);
          uiId++;
        });
      }

      onSuccess(customers);
    }, onError);
  }

  getAllProjectsForCustomer(customer: Customer, onSuccess: (projects: Project[]) => void, onError?: (status: number) => void){
    this.httpClient.get('projects/customer/get/' + customer.customerId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      let responseProjects: Project[] = [];

      for (let dataItem of r.data){
        responseProjects.push(this.mapToProject(dataItem));
      }
      onSuccess(responseProjects);
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  closeProjectsForCustomer(customer: Customer, onSuccess: (projects: Project[]) => void, onError?: (status: number) => void) : void {
    this.httpClient.get('projects/customer/close/' + customer.customerId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      let responseProjects: Project[] = [];

      for (let dataItem of r.data){
        responseProjects.push(this.mapToProject(dataItem));
      }
      onSuccess(responseProjects);
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  deleteProject(project: Project, onSuccess: (project: Project) => void, onError?: (status: number) => void) {
    if(project.projectId == undefined){
      return;
    }
    this.httpClient.delete('projects/delete', project.projectId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      this.cache.clearCache();
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
      this.cache.clearCache();
      if (onSuccess) {
        onSuccess(this.mapToProject(r.data))
      }
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  getAllProjects(onSuccess: (projects: Project[]) => void, onError?: (status: number) => void) {
    if (this.cache.isCacheFilled()){
      onSuccess(this.cache.getItems());
      return;
    }

    this.httpClient.get('projects/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responseProjects: Project[] = [];

      for (let dataItem of r.data){
        responseProjects.push(this.mapToProject(dataItem));
      }
      this.cache.setItems(responseProjects);
      onSuccess(responseProjects)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  mapToProject(data: ProjectResponseData): Project {

    return new Project(
      data.title,
      new Date(data.creationTime),
      data.projectId,
      data.shortDescription === null ? undefined : data.shortDescription,
      data.longDescription === null ? undefined : data.longDescription,
      data.startTime === null ? undefined : new Date(data.startTime),
      data.endTime === null ? undefined : new Date(data.endTime),
      data.closedTime === null ? undefined : new Date(data.closedTime),
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
  closedTime: Date | null;
  customerId: number | null;
}
