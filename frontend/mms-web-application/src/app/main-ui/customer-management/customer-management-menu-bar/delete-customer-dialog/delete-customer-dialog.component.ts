import {NgIf} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {Customer} from "../../../../../model/cutomer/customer";
import {Project} from "../../../../../model/project/project";
import {Todo} from "../../../../../model/todo/todo";
import {CustomerManagementService} from "../../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../../services/projectmanagement/projectManagementService";
import {TodoService} from "../../../../../services/todo/todoService";
import {CustomerManagementCustomer} from "../../customerManagementCustomer";

@Component({
  selector: 'app-delete-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatFormField, MatInput, NgIf, ReactiveFormsModule],
  templateUrl: './delete-customer-dialog.component.html',
  styleUrl: './delete-customer-dialog.component.css'
})
export class DeleteCustomerDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteCustomerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CustomerManagementCustomer,
    public todoService: TodoService,
    public projectService: ProjectManagementService,
    public customerService: CustomerManagementService
  ) {
  }


  ngOnInit(): void {
  }

  deleteCustomer(): void {
    let customer = this.data.customer;

    this.todoService.deleteTodosForCustomer(customer, (todos: Todo[]) => {
      //TODO
    }, status => {
      //TODO
    });

    this.projectService.closeProjectsForCustomer(customer, (projects: Project[]) => {
      //TODO
    }, status => {
      //TODO
    });

    this.customerService.deleteCustomer(customer, (customer: Customer) => {
      window.location.reload();
    }, (status: number) => {
      alert("Kunde konnte nicht gelöscht werden\n\n" +
        "Error-Code: " + status); //TODO
    });
  }
}
