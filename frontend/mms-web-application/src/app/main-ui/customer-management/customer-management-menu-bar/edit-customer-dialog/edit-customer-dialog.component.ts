import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AfterViewChecked, Component, Inject, input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
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
import {Event} from "@angular/router";
import {Address} from "../../../../../model/cutomer/address";
import {ContactPerson} from "../../../../../model/cutomer/contactPerson";
import {ContactPersonPosition} from "../../../../../model/cutomer/contactPersonPosition";
import {CountryUtil} from "../../../../../model/cutomer/country";
import {ReasonForContact} from "../../../../../model/cutomer/reasonForContact";
import {
  ContactPersonPositionService
} from "../../../../../services/customermanagement/contactpersonposition/contactPersonPositionService";
import {
  ReasonForContactService
} from "../../../../../services/customermanagement/reasonsforcontact/reasonForContactService";
import {DeepCloneService} from "../../../../../services/util/deepCloneService";
import {CustomerManagementCustomer} from "../../customerManagementCustomer";


@Component({
  selector: 'app-edit-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatTabGroup, MatTab, MatFormField, MatInput, ReactiveFormsModule, MatTabContent, MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable, NgOptimizedImage, MatHeaderCellDef, NgIf, NgClass, NgForOf, MatSelect, MatOption, FormsModule, MatTooltip],
  templateUrl: './edit-customer-dialog.component.html',
  styleUrl: './edit-customer-dialog.component.css'
})
export class EditCustomerDialogComponent implements OnInit, AfterViewChecked {

  ngAfterViewChecked(): void {
    console.log(this.contactPersons[0].reasonForContact?.reason);
  }

  countryValues: string[] = CountryUtil.values();
  positionValues: ContactPersonPosition[] = [];
  reasonValues: ReasonForContact[] = [];

  customer: CustomerManagementCustomer;
  addressId: number | undefined;

  editCustomerDataForm: FormGroup;
  customerNameNotValid: boolean = false;

  country: string;



  contactPersons: ContactPerson[];

  deleteButtonClass: string = 'secondary-button';

  constructor(public dialogRef: MatDialogRef<EditCustomerDialogComponent, CustomerManagementCustomer>,
              editCustomerDataFormBuilder: FormBuilder,
              positionService: ContactPersonPositionService,
              reasonForContactService: ReasonForContactService,
              @Inject(MAT_DIALOG_DATA) data: CustomerManagementCustomer) {

    positionService.getPositions((positions: ContactPersonPosition[]) => this.positionValues = positions);
    reasonForContactService.getReasons((reasons: ReasonForContact[]) => this.reasonValues = reasons);

    this.customer = new DeepCloneService().deepCopy(data);
    this.addressId = this.customer.customer.address?.addressId;

    this.country = CountryUtil.toString(this.customer.customer.address?.country) ?? '----';

    this.editCustomerDataForm = editCustomerDataFormBuilder.group({
      customerName: [this.customer.customer.companyName, Validators.required],
      addressStreet: [this.customer.customer.address?.street],
      addressHouseNumber: [this.customer.customer.address?.houseNumber],
      addressZipCode: [this.customer.customer.address?.zipCode],
      addressCity: [this.customer.customer.address?.city],
      addressCountry: [this.country]
    })

    this.contactPersons = this.customer.customer.contactPersons;
    if(this.contactPersons.length == 0){
      this.addContactPerson();
    }
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

    this.customer.customer.companyName = editCustomerDataValues.customerName;
    this.customer.customer.address = new Address(editCustomerDataValues.addressStreet, editCustomerDataValues.addressHouseNumber, editCustomerDataValues.addressCity, CountryUtil.fromValue(editCustomerDataValues.addressCountry), editCustomerDataValues.addressZipCode, this.addressId);

    this.customer.customer.contactPersons = this.contactPersons.filter(pers => pers.firstName != null || pers.lastName != null);
    this.dialogRef.close(this.customer)
  }


  setCustomerDataValidationConfigurations(editCustomerDataForm: FormGroup) {
    let editCustomerDataFormValues = editCustomerDataForm.value;
    this.customerNameNotValid = editCustomerDataFormValues.customerName === undefined;
  }

  deleteContactPerson(contactPerson: ContactPerson) {
    //TODO
  }

  addContactPerson() {
    this.contactPersons.push(new ContactPerson('', ''));
  }

  contactPersonFirstNameInput($event: any, contactPerson: ContactPerson) {
    contactPerson.firstName = (($event as InputEvent).target as HTMLInputElement).value;
    //TODO Error Handling with Empty Input
  }

  contactPersonPositionInput($event: MatSelectChange, contactPerson: ContactPerson) {
   if($event === undefined){
     return;
   }
   contactPerson.position = $event.value as ContactPersonPosition;
  }

  contactPersonLastNameInput($event: any, contactPerson: ContactPerson) {
    contactPerson.lastName = (($event as InputEvent).target as HTMLInputElement).value;
    //TODO Error Handling with Empty Input
  }

  contactPersonEMailInput($event: any, contactPerson: ContactPerson) {
    let input = (($event as InputEvent).target as HTMLInputElement).value;
    contactPerson.email = input === '' ? null : input;
  }

  contactPersonPhoneNumberInput($event: any, contactPerson: ContactPerson) {
    let input = (($event as InputEvent).target as HTMLInputElement).value;
    contactPerson.phoneNumber = input === '' ? null : input;
  }

  contactPersonReasonForContactInput($event: MatSelectChange, contactPerson: ContactPerson) {
    if($event === undefined){
      return;
    }
    contactPerson.reasonForContact = $event.value as ReasonForContact;
  }

  protected readonly console = console;
}
