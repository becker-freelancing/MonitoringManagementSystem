import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Component} from '@angular/core';
import {Address} from "../../../../model/cutomer/address";
import {ContactPerson} from "../../../../model/cutomer/contactPerson";
import {ContactPersonPosition} from "../../../../model/cutomer/contactPersonPosition";
import {Country} from "../../../../model/cutomer/country";
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {CustomerManagementCustomer} from "./customerManagementCustomer";

@Component({
  selector: 'app-all-customer-table',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    NgOptimizedImage
  ],
  templateUrl: './all-customer-table.component.html',
  styleUrl: './all-customer-table.component.css'
})
export class AllCustomerTableComponent {
  customers: CustomerManagementCustomer[];


  constructor() {
    this.customers = [];
  }
}
