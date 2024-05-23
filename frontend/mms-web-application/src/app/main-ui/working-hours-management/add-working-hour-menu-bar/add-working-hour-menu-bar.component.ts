import {NgForOf} from "@angular/common";
import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatOption, NativeDateAdapter} from "@angular/material/core";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatDialogRef} from "@angular/material/dialog";
import {MatFormField, MatInput, MatLabel, MatSuffix} from "@angular/material/input";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {NgxMatTimepickerComponent, NgxMatTimepickerDirective} from "ngx-mat-timepicker";
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {CUSTOM_DATE_FORMATS} from "../../../../model/util/dateFormats";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";

@Component({
  selector: 'app-add-working-hour-menu-bar',
  standalone: true,
  imports: [
    NgxMatTimepickerComponent,
    NgxMatTimepickerDirective,
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatInput,
    MatLabel,
    MatSuffix,
    ReactiveFormsModule,
    MatFormField,
    MatOption,
    MatSelect,
    NgForOf
  ],
  templateUrl: './add-working-hour-menu-bar.component.html',
  styleUrl: './add-working-hour-menu-bar.component.css',
  providers: [
    { provide: DateAdapter, useClass: NativeDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS }
  ]
})
export class AddWorkingHourMenuBarComponent {

  allCustomers: Customer[] = [];
  projectsForCustomer: Project[] = [];

  addWorkingHourForm: FormGroup;

  workingHourDate: Date = new Date();
  workingHourEndTime: string = '';
  workingHourStartTime: string = '';

  constructor(
    fb: FormBuilder,
    customerService: CustomerManagementService,
    public projectService: ProjectManagementService) {

    this.addWorkingHourForm = fb.group({
      workingHourDate: [this.workingHourDate, Validators.required],
      workingHourStartTime: [this.workingHourStartTime, Validators.required],
      workingHourEndTime: [this.workingHourEndTime]
    });

    customerService.getAllCustomers((customers) => this.allCustomers = customers);
  }

  getProjectsForCustomer(customer: Customer){
    this.projectService.getAllProjectsForCustomer(customer, (projects) => this.projectsForCustomer = projects);
  }

  save(){
    console.log(this.workingHourDate)
    console.log(this.workingHourEndTime)
    console.log(this.workingHourStartTime)
  }

  customerChanges($event: any) {
    console.log($event)
  }
}
