import {Component, Input} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {Updatable} from "../../../../model/util/updatable";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {CustomerManagementCustomer} from "../customerManagementCustomer";
import {AddCustomerDialogComponent} from "./add-customer-dialog/add-customer-dialog.component";
import {DeleteCustomerDialogComponent} from "./delete-customer-dialog/delete-customer-dialog.component";
import {EditCustomerDialogComponent} from "./edit-customer-dialog/edit-customer-dialog.component";

@Component({
  selector: 'app-customer-management-menu-bar',
  standalone: true,
  imports: [MatButtonModule],
  templateUrl: './customer-management-menu-bar.component.html',
  styleUrl: './customer-management-menu-bar.component.css'
})
export class CustomerManagementMenuBarComponent implements Updatable{

  @Input('currentlySelectedCustomer') currentlySelectedCustomer: CustomerManagementCustomer | null = null;

  customerManagementService: CustomerManagementService;

  constructor(
    public dialog: MatDialog,
    customerManagementService: CustomerManagementService) {
    this.customerManagementService = customerManagementService;
  }

  openAddCustomerDialog(): void {
    let dialogRef = this.dialog.open(AddCustomerDialogComponent, {
      width: '400px',
      height: '85%'
    });

    dialogRef.afterClosed().subscribe(customer => {
      this.customerManagementService.addCustomer(customer);
      this.update();
    })

  }

  openEditCustomerDialog(customer: CustomerManagementCustomer | null) {
    if (customer == null){
      return;
    }

    this.dialog.open(EditCustomerDialogComponent, {
      data: customer
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
