import { Component } from '@angular/core';
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

}
