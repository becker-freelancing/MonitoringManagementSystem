import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
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
import {Address} from "../../../../../model/cutomer/address";
import {Country, CountryUtil} from "../../../../../model/cutomer/country";
import {CustomerManagementService} from "../../../../../services/customermanagement/customerManagementService";
import {DeepCloneService} from "../../../../../services/util/deepCloneService";
import {CustomerManagementCustomer} from "../../customerManagementCustomer";


@Component({
  selector: 'app-edit-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatTabGroup, MatTab, MatFormField, MatInput, ReactiveFormsModule, MatTabContent, MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable, NgOptimizedImage, MatHeaderCellDef, NgIf, NgClass, NgForOf],
  templateUrl: './edit-customer-dialog.component.html',
  styleUrl: './edit-customer-dialog.component.css'
})
export class EditCustomerDialogComponent implements OnInit {

  countryValues: string[] = CountryUtil.values();

  customer: CustomerManagementCustomer;
  addressId: number | undefined;

  editCustomerDataForm: FormGroup;
  customerNameNotValid: boolean = false;


  constructor(public dialogRef: MatDialogRef<EditCustomerDialogComponent>,
              editCustomerDataFormBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) data: CustomerManagementCustomer) {

    this.customer = new DeepCloneService().deepCopy(data);
    this.addressId = this.customer.customer.address?.addressId;

    this.editCustomerDataForm = editCustomerDataFormBuilder.group({
      customerName: [this.customer.customer.companyName, Validators.required],
      addressStreet: [this.customer.customer.address?.street],
      addressHouseNumber: [this.customer.customer.address?.houseNumber],
      addressZipCode: [this.customer.customer.address?.zipCode],
      addressCity: [this.customer.customer.address?.city],
      addressCountry: [this.customer.customer.address?.country]
    })
  }

  ngOnInit(): void {
  }

  close() {
    //TODO Confirm Close Dialog
  }

  save() {
    this.setCustomerDataValidationConfigurations(this.editCustomerDataForm);
    if (!this.editCustomerDataForm.valid) {
      return;
    }

    let editCustomerDataValues = this.editCustomerDataForm.value;
    console.log(editCustomerDataValues)

    this.customer.customer.companyName = editCustomerDataValues.customerName;
    this.customer.customer.address = new Address(editCustomerDataValues.addressStreet, editCustomerDataValues.addressHouseNumber, editCustomerDataValues.addressCity, CountryUtil.fromValue(editCustomerDataValues.addressCountry), editCustomerDataValues.addressZipCode, this.addressId);

    this.dialogRef.close(this.customer)
  }


  setCustomerDataValidationConfigurations(editCustomerDataForm: FormGroup) {
    let editCustomerDataFormValues = editCustomerDataForm.value;
    this.customerNameNotValid = editCustomerDataFormValues.customerName === undefined;
  }
}
