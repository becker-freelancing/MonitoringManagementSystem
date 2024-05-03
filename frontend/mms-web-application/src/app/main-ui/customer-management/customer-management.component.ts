import {Component, ElementRef, ViewChild} from '@angular/core';
import {CustomerManagementCustomer} from "../../../model/customerManagementCustomer";
import {AllCustomerTableComponent} from "./all-customer-table/all-customer-table.component";
import {
  CustomerManagementMenuBarComponent
} from "./customer-management-menu-bar/customer-management-menu-bar.component";

@Component({
  selector: 'app-customer-management',
  standalone: true,
  imports: [
    CustomerManagementMenuBarComponent,
    AllCustomerTableComponent
  ],
  templateUrl: './customer-management.component.html',
  styleUrl: './customer-management.component.css'
})
export class CustomerManagementComponent {

  @ViewChild('customerManagementMenuBar') menuBar?: CustomerManagementMenuBarComponent;

  currentlySelectedCustomer: CustomerManagementCustomer | null = null;

  onDoubleClickedCustomer(customer: CustomerManagementCustomer) {
    this.currentlySelectedCustomer = customer;
    this.menuBar?.openEditCustomerDialog(customer);
  }

  changeSelectedCustomerProperty(customer: CustomerManagementCustomer) {
    this.currentlySelectedCustomer = customer;
  }
}
