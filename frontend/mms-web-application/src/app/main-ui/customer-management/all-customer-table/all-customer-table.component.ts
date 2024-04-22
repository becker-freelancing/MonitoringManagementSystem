import {CdkScrollable} from "@angular/cdk/overlay";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AfterViewInit, Component, EventEmitter, Output} from '@angular/core';
import {
  MatCell,
  MatCellDef, MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {Customer} from "../../../../model/cutomer/customer";
import {Updatable} from "../../../../model/util/updatable";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
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
    CdkScrollable
  ],
  templateUrl: './all-customer-table.component.html',
  styleUrl: './all-customer-table.component.css'
})
export class AllCustomerTableComponent implements AfterViewInit, Updatable{

  @Output('selectedCustomer') selectedCustomer = new EventEmitter<CustomerManagementCustomer>();
  @Output('dblClickedCustomer') dblClickedCustomer = new EventEmitter<CustomerManagementCustomer>();

  customerService: CustomerManagementService;

  customers: CustomerManagementCustomer[];
  displayedColumns: string[] = ['id', 'customerName', 'activeTodos', 'activeProjects'];

  constructor(customerService: CustomerManagementService) {
    this.customers = [];
    this.customerService = customerService;
  }


  ngAfterViewInit(): void {
    this.update();
  }

  update(): void{
    this.customerService.getAllCustomers((customers: Customer[]): void => {
      this.customers = [];
      for (let customer of customers){
        let customerManagementCustomer = new CustomerManagementCustomer(customer);
        this.customers.push(customerManagementCustomer);
      }
    }, (status: number) => alert(status))
  }

  emitSingleClickedCustomerEvent(customer: CustomerManagementCustomer){
   this.selectedCustomer.emit(customer);
  }

  emitDblClickedCustomerEvent(customer: CustomerManagementCustomer) {
    this.dblClickedCustomer.emit(customer);
  }
}
