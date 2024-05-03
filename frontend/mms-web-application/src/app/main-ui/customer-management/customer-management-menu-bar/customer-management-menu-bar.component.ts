import {NgClass} from "@angular/common";
import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {CustomerManagementCustomer} from "../../../../model/customerManagementCustomer";
import {Updatable} from "../../../../model/util/updatable";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {AddCustomerDialogComponent} from "./add-customer-dialog/add-customer-dialog.component";
import {DeleteCustomerDialogComponent} from "./delete-customer-dialog/delete-customer-dialog.component";
import {EditCustomerDialogComponent} from "./edit-customer-dialog/edit-customer-dialog.component";

@Component({
  selector: 'app-customer-management-menu-bar',
  standalone: true,
  imports: [MatButtonModule, NgClass],
  templateUrl: './customer-management-menu-bar.component.html',
  styleUrl: './customer-management-menu-bar.component.css'
})
export class CustomerManagementMenuBarComponent implements Updatable, OnChanges{

  @Input('currentlySelectedCustomer') currentlySelectedCustomer: CustomerManagementCustomer | null = null;

  customerManagementService: CustomerManagementService;
  editButtonClass: string = 'disabled-button';

  constructor(
    public dialog: MatDialog,
    customerManagementService: CustomerManagementService) {
    this.customerManagementService = customerManagementService;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["currentlySelectedCustomer"]){
      let newCustomer = changes['currentlySelectedCustomer'].currentValue;
      if(newCustomer != null){
        this.editButtonClass = 'secondary-button';
      }
    }
  }

  openAddCustomerDialog(): void {
    let dialogRef = this.dialog.open(AddCustomerDialogComponent, {
      width: '400px',
      height: '85%'
    });

    dialogRef.afterClosed().subscribe(customer => {
      this.customerManagementService.saveCustomer(customer);
      this.update();
    })

  }

  openEditCustomerDialog(customer: CustomerManagementCustomer | null) {
    if (customer == null){
      return;
    }

    this.dialog.open(EditCustomerDialogComponent, {
      width: '90%',
      height: '90%',
      data: customer
    }).afterClosed().subscribe(customer => {
      this.customerManagementService.saveCustomer(customer.customer);
      window.location.reload();
    });
  }

  openDeleteCustomerDialog(customer: CustomerManagementCustomer | null) {
    if(customer == null){
      return;
    }

    this.dialog.open(DeleteCustomerDialogComponent, {
      data: customer
    });
  }

  update(): void {
    window.location.reload();
  }


}
