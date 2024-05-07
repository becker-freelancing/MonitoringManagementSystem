import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatNativeDateModule,
  NativeDateAdapter
} from "@angular/material/core";
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatHint, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect, MatSelectChange} from "@angular/material/select";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatTab, MatTabContent, MatTabGroup} from "@angular/material/tabs";
import {MatTooltip} from "@angular/material/tooltip";

export const MY_CUSTOM_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-edit-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatTabGroup, MatTab, MatFormField, MatInput, ReactiveFormsModule, MatTabContent, MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable, NgOptimizedImage, MatHeaderCellDef, NgIf, NgClass, NgForOf, MatSelect, MatOption, FormsModule, MatTooltip, MatDatepickerToggle, MatDatepickerInput, MatDatepicker, MatHint, MatLabel, MatIcon, MatSuffix, MatNativeDateModule, MatDatepickerModule],
  templateUrl: './edit-todo-dialog.component.html',
  styleUrl: './edit-todo-dialog.component.css',
  providers: [
    { provide: DateAdapter, useClass: NativeDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_CUSTOM_DATE_FORMATS }
  ]
})
export class EditTodoDialogComponent implements OnInit {
  //
  // customers: Customer[] = [];
  //
  // project: ProjectManagementProject;
  //
  // editProjectDataForm: FormGroup;
  // projectNameNotValid: boolean = false;
  //
  // projectCustomerLocked: boolean = true;
  // currentlySelectedCustomer: Customer | undefined;
  //
  // customer: Customer | undefined;


  constructor(
    // public dialogRef: MatDialogRef<EditProjectDialogComponent, ProjectManagementProject>,
    //           public confirmDialogService: ConfirmDialogService,
    //           editProjectDataFormBuilder: FormBuilder,
    //           public customerManagementService: CustomerManagementService,
    //           @Inject(MAT_DIALOG_DATA) data: ProjectManagementProject
  ) {

    // this.project = new DeepCloneService().deepCopy(data);
    //
    // this.editProjectDataForm = editProjectDataFormBuilder.group({
    //   title: [this.project.project.title, Validators.required],
    //   shortDescription: [this.project.project.shortDescription],
    //   longDescription: [this.project.project.longDescription],
    //   startTime: [this.project.project.startTime],
    //   endTime: [this.project.project.endTime]
    // });
    //
    // this.getCustomerName();
    // this.getAllCustomers();
  }

  ngOnInit(): void {
  }

  close() {
    // this.confirmDialogService.showConfirmCancelDialog(() => this.dialogRef.close(), () => {});
  }

  save() {
    // if (!this.editProjectDataForm.valid) {
    //   this.projectNameNotValid = this.editProjectDataForm.value.title === undefined;
    //   return;
    // }
    //
    // let editProjectDataValues = this.editProjectDataForm.value;
    //
    // this.project.project.title = editProjectDataValues.title;
    // this.project.project.shortDescription = editProjectDataValues.shortDescription;
    // this.project.project.longDescription = editProjectDataValues.longDescription;
    // this.project.project.startTime = editProjectDataValues.startTime;
    // this.project.project.endTime = editProjectDataValues.endTime;
    //
    // this.dialogRef.close(this.project)
  }

  changeProjectCustomer() {
    // this.projectCustomerLocked = false;
  }

  getCustomerName() {
    // if (this.project.project.customerId === undefined){
    //   return;
    // }
    //
    // this.customerManagementService.getCustomer(this.project.project.customerId, (customer: Customer) => {this.customer = customer})
  }

  goToCustomer() {
    //TODO
  }

  customerChanges($event: MatSelectChange) {
    // this.currentlySelectedCustomer = $event.value;
  }

  getAllCustomers() {
    // this.customerManagementService.getAllCustomers((customers: Customer[]) => {this.customers = customers})
  }

  connectCustomer() {
    // this.project.project.customerId = this.currentlySelectedCustomer?.customerId;
    // this.getCustomerName();
    // this.projectCustomerLocked = true;
  }
}
