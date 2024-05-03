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
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {Updatable} from "../../../../model/util/updatable";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {CustomerManagementCustomer} from "../customerManagementCustomer";

@Component({
  selector: 'app-all-customer-table',
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
  templateUrl: './all-customer-table.component.html',
  styleUrl: './all-customer-table.component.css'
})
export class AllCustomerTableComponent implements AfterViewInit, Updatable {

  @Output('selectedCustomer') selectedCustomer = new EventEmitter<CustomerManagementCustomer>();
  @Output('dblClickedCustomer') dblClickedCustomer = new EventEmitter<CustomerManagementCustomer>();

  customerService: CustomerManagementService;

  customers: CustomerManagementCustomer[];
  displayedColumns: string[] = ['id', 'customerName', 'activeTodos', 'activeProjects'];

  currentlySelectedCustomerUiId: number = -1;
  selectedTableCell: string = 'mat-mdc-row-selected';

  constructor(customerService: CustomerManagementService,
              public projectManagementService: ProjectManagementService) {
    this.customers = [];
    this.customerService = customerService;
  }


  ngAfterViewInit(): void {
    this.update();
  }

  update(): void {
    this.customerService.getAllCustomers((customers: Customer[]): void => {
      this.customers = [];
      let uiId = 0;
      for (let customer of customers) {
        uiId++;
        let customerManagementCustomer = new CustomerManagementCustomer(uiId, customer);
        this.customers.push(customerManagementCustomer);
      }

      for (let customer of this.customers) {
        this.projectManagementService.getAllProjectsForCustomer(customer.customer, (projects: Project[]) => {
          customer.projects = projects;
        });
      }
    }, (status: number) => alert(status));

  }

  onCustomerSingleClicked(customer: CustomerManagementCustomer) {
    this.currentlySelectedCustomerUiId = customer.uiId;
    this.selectedCustomer.emit(customer);
  }

  emitDblClickedCustomerEvent(customer: CustomerManagementCustomer) {
    this.dblClickedCustomer.emit(customer);
  }
}
