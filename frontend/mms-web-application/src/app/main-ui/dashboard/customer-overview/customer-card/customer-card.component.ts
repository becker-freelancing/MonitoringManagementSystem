import {DatePipe} from "@angular/common";
import {AfterViewInit, ChangeDetectorRef, Component, Input} from '@angular/core';
import {CustomerManagementCustomer} from "../../../../../model/customerManagementCustomer";
import {Customer} from "../../../../../model/cutomer/customer";
import {Project} from "../../../../../model/project/project";

@Component({
  selector: 'app-customer-card',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './customer-card.component.html',
  styleUrl: './customer-card.component.css'
})
export class CustomerCardComponent implements AfterViewInit{

  @Input({required: true, alias: 'customer'}) customerManagementCustomer!: CustomerManagementCustomer;

  customer: Customer | undefined;
  project: Project | undefined;

  constructor(private changeDetectorRef: ChangeDetectorRef) {
  }

  ngAfterViewInit(): void {
    this.customer = this.customerManagementCustomer.customer;
    this.project = this.customerManagementCustomer.projects[0];

    this.changeDetectorRef.detectChanges();
  }


}
