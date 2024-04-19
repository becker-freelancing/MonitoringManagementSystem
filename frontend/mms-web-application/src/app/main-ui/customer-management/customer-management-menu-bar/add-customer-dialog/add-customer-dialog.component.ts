import {NgForOf} from "@angular/common";
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {Address} from "../../../../../model/cutomer/address";
import {Country} from "../../../../../model/cutomer/country";
import {Customer} from "../../../../../model/cutomer/customer";
import {CustomerManagementService} from "../../../../../services/customermanagement/customerManagementService";

@Component({
  selector: 'app-add-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, NgForOf, FormsModule],
  templateUrl: './add-customer-dialog.component.html',
  styleUrl: './add-customer-dialog.component.css',
  providers: [CustomerManagementService]
})
export class AddCustomerDialogComponent implements OnInit{

  countries: string[];
  positions: string[];
  reasonsForContact: string[];

  customerName: string = '';
  street: string = '';
  houseNumber: string = '';
  zipCode: string = '';
  city: string = '';
  country: string = '';

  constructor(public dialogRef: MatDialogRef<AddCustomerDialogComponent>, private customerService: CustomerManagementService) {
    this.countries = this.getEnumRepresentationValues(Country);
    this.positions = [];
    this.reasonsForContact = [];
  }

  getEnumRepresentationValues(enumType: any): string[] {
    return Object.keys(enumType)
      .filter(key => !isNaN(Number(enumType[key])))
      .map(key => enumType[key]);
  }

  getEnumValue(value: string, enumType: any): Country | null {
    const keys = Object.keys(Country).filter(x => enumType[x] === value);
    if (keys.length > 0) {
      return enumType[keys[0]];
    } else {
      return null;
    }
  }

  ngOnInit(): void {
  }

  saveCustomer() {
    let country = this.getEnumValue(this.country, Country);
    let address = new Address(this.street, this.houseNumber, this.city, country, this.zipCode);
    let customer = new Customer(this.customerName, address);

    this.customerService.addCustomer(customer);
  }


}
