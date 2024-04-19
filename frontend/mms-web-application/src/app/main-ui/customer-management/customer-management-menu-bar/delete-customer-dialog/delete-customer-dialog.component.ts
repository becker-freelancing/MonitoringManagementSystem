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
  selector: 'app-delete-customer-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  templateUrl: './delete-customer-dialog.component.html',
  styleUrl: './delete-customer-dialog.component.css'
})
export class DeleteCustomerDialogComponent implements OnInit{

  constructor(public dialogRef: MatDialogRef<DeleteCustomerDialogComponent>) {
  }


  ngOnInit(): void {
  }
}
