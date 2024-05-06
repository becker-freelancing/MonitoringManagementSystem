import {AfterViewInit, Component, OnInit} from '@angular/core';
import {CustomerManagementCustomer} from "../../../../model/customerManagementCustomer";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {CustomerCardComponent} from "./customer-card/customer-card.component";

@Component({
  selector: 'customer-overview',
  standalone: true,
  imports: [
    CustomerCardComponent
  ],
  templateUrl: './customer-overview.component.html',
  styleUrl: './customer-overview.component.css'
})
export class CustomerOverviewComponent implements AfterViewInit{

  customersWithOpenProjects: CustomerManagementCustomer[] = [];

  constructor(public customerManagementService: ProjectManagementService) {
  }


  ngAfterViewInit(): void {
    this.customerManagementService.getAllCustomersWithOpenProjects((customers) => {
      this.customersWithOpenProjects = customers;
      }, () => this.customersWithOpenProjects = []);
  }

}
