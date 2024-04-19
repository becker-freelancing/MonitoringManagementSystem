import {Component, OnInit} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
@Component({
  selector: 'app-edit-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  templateUrl: './edit-customer-dialog.component.html',
  styleUrl: './edit-customer-dialog.component.css'
})
export class EditCustomerDialogComponent implements OnInit{

  contactPersons: {
    positionId: string,
    firstName: string,
    lastName: string,
    eMails: string[],
    phoneNumbers: string[],
    reasonForContact: string[]
  }[];

  constructor(public dialogRef: MatDialogRef<EditCustomerDialogComponent>) {

    this.contactPersons = [];
  }

  addContactPerson() {
    this.contactPersons.push({
      positionId: '',
      firstName: '',
      lastName: '',
      eMails: [],
      phoneNumbers: [],
      reasonForContact: []
    })
  }

  ngOnInit(): void {
  }
}
