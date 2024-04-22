import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AfterViewChecked, AfterViewInit, Component} from '@angular/core';
import {Address} from "../../../../model/cutomer/address";
import {ContactPerson} from "../../../../model/cutomer/contactPerson";
import {ContactPersonPosition} from "../../../../model/cutomer/contactPersonPosition";
import {Country} from "../../../../model/cutomer/country";
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {Updatable} from "../../../../model/util/updatable";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
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
export class AllCustomerTableComponent implements AfterViewInit, Updatable{

  customerService: CustomerManagementService;

  customers: CustomerManagementCustomer[];

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
}
