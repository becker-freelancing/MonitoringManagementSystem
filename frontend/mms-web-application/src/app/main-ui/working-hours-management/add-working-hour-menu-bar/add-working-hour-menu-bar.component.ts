import {NgForOf, NgIf} from "@angular/common";
import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatOption, NativeDateAdapter} from "@angular/material/core";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatDialog} from "@angular/material/dialog";
import {MatFormField, MatInput, MatLabel, MatSuffix} from "@angular/material/input";
import {MatSelect} from "@angular/material/select";
import {NgxMatTimepickerComponent, NgxMatTimepickerDirective} from "ngx-mat-timepicker";
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {CDate} from "../../../../model/util/cDate";
import {CUSTOM_DATE_FORMATS} from "../../../../model/util/dateFormats";
import {Time} from "../../../../model/util/time";
import {WorkingHour} from "../../../../model/workinghours/workingHour";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {WorkingHourService} from "../../../../services/workinghourmanagement/workingHourService";
import {WorkingHourSyncService} from "../../../../services/workinghourmanagement/workingHourSyncService";
import {
  AddCustomerDialogComponent
} from "../../customer-management/customer-management-menu-bar/add-customer-dialog/add-customer-dialog.component";
import {
  WorkingHourExistsWarningDialogComponent, WorkingHourExistsWarningDialogData, WorkingHourExistsWorkingHour
} from "./working-hour-exists-warning-dialog/working-hour-exists-warning-dialog.component";

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
    NgForOf,
    FormsModule,
    NgIf
  ],
  templateUrl: './add-working-hour-menu-bar.component.html',
  styleUrl: './add-working-hour-menu-bar.component.css',
  providers: [
    {provide: DateAdapter, useClass: NativeDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS}
  ]
})
export class AddWorkingHourMenuBarComponent {

  allCustomers: Customer[] = [];
  projectsForCustomer: Project[] = [];
  currentDate: string;

  workingHourDate?: string;
  workingHourDateValid: boolean = true;
  workingHourEndTime?: Time;
  workingHourEndTimeValid: boolean = true;
  workingHourStartTime?: Time;
  workingHourStartTimeValid: boolean = true;
  workingHourDuration: string = '---';
  workingHourDurationValid: boolean = true;
  customer?: Customer;
  customerValid: boolean = true;
  project?: Project;
  projectValid: boolean = true;

  constructor(
    customerService: CustomerManagementService,
    public projectService: ProjectManagementService,
    public workingHourService: WorkingHourService,
    public workingHourSyncService: WorkingHourSyncService,
    public dialog: MatDialog) {

    customerService.getAllCustomers((customers) => this.allCustomers = customers);
    this.currentDate = new Date().toISOString().split("T")[0];
    this.workingHourDate = this.currentDate;
  }

  save() {
    if(!this.validate()){
      return;
    }

    let overlapped = this.checkForOverlappingWorkingHours(this.recordWorkingHour, this);

    if(!overlapped){
      this.recordWorkingHour(this);
    }
  }

  private recordWorkingHour(that: AddWorkingHourMenuBarComponent){

    if (!(that.workingHourDate && that.workingHourStartTime && that.customer && that.project && that.project.projectId)) {
      return;
    }

    let workingHour = new WorkingHour(
      CDate.fromDateString(that.workingHourDate),
      that.workingHourStartTime,
      that.customer.customerId,
      that.project.projectId,
      that.workingHourEndTime
    );

    that.workingHourService.save(workingHour, (workingHour: WorkingHour) => {
      that.workingHourSyncService.addWorkingHour(workingHour);
    })
  }

  customerChanges($event: any) {
    let customer = this.searchCustomerByName($event.target.value);
    this.customer = customer;
    if (customer === undefined) {
      this.projectsForCustomer = [];
      return;
    }

    this.projectService.getAllProjectsForCustomer(customer, (projects) => {
      this.projectsForCustomer = projects
    });
  }

  toTimeChangedEvent($event: any) {
    this.workingHourEndTime = Time.fromTimeString($event.target.value);
    this.calcFromToTimeDuration();
  }

  fromTimeChangedEvent($event: any) {
    this.workingHourStartTime = Time.fromTimeString($event.target.value);
    this.calcFromToTimeDuration();
  }

  calcFromToTimeDuration(): void {
    if (!this.workingHourStartTime || !this.workingHourEndTime) {
      this.workingHourDuration = '----';
      return;
    }

    let duration = this.workingHourStartTime.calcAbsoluteDurationTo(this.workingHourEndTime);

    this.workingHourDuration = duration.hours + 'h ' + duration.minutes + 'min';
  }

  dateChangedEvent($event: any) {
    this.workingHourDate = $event.target.value;
  }

  projectChanges($event: any) {
    this.project = this.searchProjectByName($event.target.value);
  }

  private searchCustomerByName(name: string): Customer | undefined {
    for (const customer of this.allCustomers) {
      if (customer.companyName == name) {
        return customer;
      }
    }

    return undefined;
  }

  private searchProjectByName(name: string): Project | undefined {
    for (const project of this.projectsForCustomer) {
      if (project.title == name) {
        return project;
      }
    }

    return undefined;
  }

  private validate(): boolean {
    this.workingHourDateValid = this.workingHourDate != undefined;
    this.workingHourStartTimeValid = this.workingHourStartTime != undefined;
    this.workingHourEndTimeValid = this.workingHourEndTime != undefined;
    if(this.workingHourStartTime && this.workingHourEndTime) {
      this.workingHourDurationValid = this.workingHourStartTime.isBefore(this.workingHourEndTime);
    } else {
      this.workingHourDurationValid = false;
    }
    this.customerValid = this.customer != undefined;
    this.projectValid = this.project != undefined;

    return this.workingHourDateValid &&
      this.workingHourStartTimeValid &&
      this.workingHourEndTimeValid &&
      this.workingHourDurationValid &&
      this.customerValid &&
      this.projectValid;
  }

  private checkForOverlappingWorkingHours(onRecordWorkingHour: (that: AddWorkingHourMenuBarComponent) => void, that: AddWorkingHourMenuBarComponent): boolean {
    if(!this.workingHourDate){
      return true;
    }

    let recordedNewDate = CDate.fromDateString(this.workingHourDate);

    let overlapped = this.workingHourSyncService.getWorkingHours()
      .filter(workingHour => workingHour.workingHour.date.isEqual(recordedNewDate))
      .filter(workingHour =>
        (workingHour.workingHour.startTime.isBefore(this.workingHourStartTime) &&
          workingHour.workingHour.endTime?.isAfter(this.workingHourStartTime)) ||
        (workingHour.workingHour.startTime.isBefore(this.workingHourEndTime) &&
          workingHour.workingHour.endTime?.isAfter(this.workingHourEndTime)) ||
        (workingHour.workingHour.startTime.isEqual(this.workingHourStartTime) ||
          workingHour.workingHour.startTime.isEqual(this.workingHourEndTime)) ||
        (workingHour.workingHour.endTime?.isEqual(this.workingHourStartTime) ||
          workingHour.workingHour.endTime?.isEqual(this.workingHourEndTime))
      )
      .map(workingHour => new class implements WorkingHourExistsWorkingHour {
        customer?: Customer = that.findCustomerById(workingHour.workingHour.customerId);
        date?: CDate = workingHour.workingHour.date;
        endTime?: Time = workingHour.workingHour.endTime;
        project?: Project = that.findProjectById(workingHour.workingHour.projectId);
        startTime?: Time = workingHour.workingHour.startTime;
      })

    if(overlapped.length == 0){
      return false;
    }

    let newWorkingHour = new class implements WorkingHourExistsWorkingHour {
      customer?: Customer = that.customer;
      date?: CDate = CDate.fromDateString(that.workingHourDate);
      endTime?: Time = that.workingHourEndTime;
      project?: Project = that.project;
      startTime?: Time = that.workingHourStartTime;
    }



    let dialogData: WorkingHourExistsWarningDialogData = new class implements WorkingHourExistsWarningDialogData {
      newWorkingHour: WorkingHourExistsWorkingHour = newWorkingHour;
      overlappedWorkingHours: WorkingHourExistsWorkingHour[] = overlapped;
    }

    let dialogRef = this.dialog.open(WorkingHourExistsWarningDialogComponent, {
      width: '500px',
      height: '500px',
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(recordWorkingHour => {
      if(recordWorkingHour){
        onRecordWorkingHour(that);
      }
    })

    return true;
  }

  findCustomerById(id: number): Customer{
    return this.allCustomers.filter(customer => customer.customerId == id)[0];
  }

  findProjectById(id: number): Project{
    return this.projectsForCustomer.filter(project => project.projectId == id)[0];
  }
}
