import {Injectable} from "@angular/core";
import {Customer} from "../../model/cutomer/customer";
import {Project} from "../../model/project/project";

@Injectable({
  providedIn: 'root',
})
export class ProjectManagementService {

  closeProjectsForCustomer(customer: Customer, onSuccess: (projects: Project[]) => void, onError: (status: number) => void) : void {

  }

}
