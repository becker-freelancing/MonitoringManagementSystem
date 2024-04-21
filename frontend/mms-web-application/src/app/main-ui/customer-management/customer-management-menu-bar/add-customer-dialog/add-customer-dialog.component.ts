import {NgForOf, NgIf} from "@angular/common";
import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose, MatDialogContainer,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {Address} from "../../../../../model/cutomer/address";
import {ContactPerson} from "../../../../../model/cutomer/contactPerson";
import {Country} from "../../../../../model/cutomer/country";
import {Customer} from "../../../../../model/cutomer/customer";
import {CustomerManagementService} from "../../../../../services/customermanagement/customerManagementService";

@Component({
  selector: 'app-add-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, NgForOf, FormsModule, MatDialogContainer, MatFormField, MatInput, ReactiveFormsModule, NgIf, MatRadioGroup, MatRadioButton],
  templateUrl: './add-customer-dialog.component.html',
  styleUrl: './add-customer-dialog.component.css',
  providers: [CustomerManagementService]
})
export class AddCustomerDialogComponent implements OnInit{

  @Output('savedCustomer') savedCustomerOutput = new EventEmitter<Customer>();

  form: FormGroup;

  customerName: string = '';
  contactPersonFirstName: string = '';
  contactPersonLastName: string = '';
  contactPersonEMail: string = '';
  contactPersonPhoneNumber: string = '';

  customerNameNotValid: boolean = false;

  constructor(
    fb: FormBuilder,
    private dialogRef: MatDialogRef<AddCustomerDialogComponent>) {
    this.form = fb.group({
      customerName: [this.customerName, Validators.required],
      contactPersonFirstName: [this.contactPersonFirstName],
      contactPersonLastName: [this.contactPersonLastName],
      contactPersonEMail: [this.contactPersonEMail],
      contactPersonPhoneNumber: [this.contactPersonPhoneNumber]
    });
  }

  ngOnInit() {

  }

  saveCustomer() {
    if(this.form.valid) {
      let formValues = this.form.value;

      let contactPeron: ContactPerson[]  = [];
      if(formValues['contactPersonFirstName'] != null && formValues['contactPersonLastName'] != null){
        let email: string[] = formValues['contactPersonEMail'] == '' ? [] : [formValues['contactPersonEMail']]
        let phoneNumber: string[] = formValues['contactPersonPhoneNumber'] == '' ? [] : [formValues['contactPersonPhoneNumber']]
        contactPeron.push(new ContactPerson(formValues['contactPersonFirstName'], formValues['contactPersonLastName'], email, phoneNumber));
      }

      let customer = new Customer(formValues['customerName'], undefined, contactPeron);
      this.dialogRef.close(customer);
    } else {
      this.customerNameNotValid = true;
    }
  }
}
