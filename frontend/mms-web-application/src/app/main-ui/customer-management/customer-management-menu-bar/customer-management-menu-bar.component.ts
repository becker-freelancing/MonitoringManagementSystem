import {Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
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
export class CustomerManagementMenuBarComponent {

  constructor(public dialog: MatDialog) {
  }

  openAddCustomerDialog(): void {
    this.dialog.open(AddCustomerDialogComponent, {});
  }

  openEditCustomerDialog() {
    this.dialog.open(EditCustomerDialogComponent, {})
  }

  openDeleteCustomerDialog() {
    this.dialog.open(DeleteCustomerDialogComponent, {})
  }
}
