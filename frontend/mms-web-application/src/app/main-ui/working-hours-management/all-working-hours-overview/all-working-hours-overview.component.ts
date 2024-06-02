import {DatePipe, NgIf} from "@angular/common";
import {Component} from '@angular/core';
import {Customer} from "../../../../model/cutomer/customer";
import {Project} from "../../../../model/project/project";
import {CDate} from "../../../../model/util/cDate";
import {WorkingHourManagementWorkingHour} from "../../../../model/workinghours/workingHourManagementWorkingHour";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {ProjectManagementService} from "../../../../services/projectmanagement/projectManagementService";
import {WorkingHourService} from "../../../../services/workinghourmanagement/workingHourService";
import {WorkingHourSyncService} from "../../../../services/workinghourmanagement/workingHourSyncService";

@Component({
  selector: 'app-all-working-hours-overview',
  standalone: true,
  imports: [
    DatePipe,
    NgIf
  ],
  templateUrl: './all-working-hours-overview.component.html',
  styleUrl: './all-working-hours-overview.component.css'
})
export class AllWorkingHoursOverviewComponent {

  sortedWorkingHours: WorkingHourSortWrapper = new WorkingHourSortWrapper();
  customers: Map<number, Customer> = new Map<number, Customer>();
  projects: Map<number, Project> = new Map<number, Project>();

  constructor(
    workingHourService: WorkingHourService,
    workingHourSyncService: WorkingHourSyncService,
    public projectService: ProjectManagementService,
    public customerService: CustomerManagementService
  ) {

    workingHourSyncService.addAddWorkingHourSubscriber(workingHours => this.onWorkingHoursChange(workingHours))
    workingHourSyncService.addEditWorkingHourSubscriber(workingHours => this.onWorkingHoursChange(workingHours))
    workingHourSyncService.addDeleteWorkingHourSubscriber(workingHours => this.onWorkingHoursChange(workingHours))

    workingHourService.getAll(workingHours => {
      workingHourSyncService.setWorkingHours(workingHours);
    })
  }


  private onWorkingHoursChange(workingHours: WorkingHourManagementWorkingHour[]) {

    if(workingHours.length == 0){
      return;
    }

    let sortWrapper: WorkingHourSortWrapper = new WorkingHourSortWrapper();
    let weekSortWrapper: WeekSortWrapper = new WeekSortWrapper(workingHours[0].workingHour.date);
    let daySortWrapper: DaySortWrapper= new DaySortWrapper(workingHours[0].workingHour.date);

    let addedDays: Set<string> = new Set<string>();

    for (const workingHour of workingHours) {

      let sameDay: WorkingHourManagementWorkingHour[] = [];

      if(addedDays.has(workingHour.workingHour.date.toString())){
        continue;
      }

      addedDays.add(workingHour.workingHour.date.toString());

      for (const other of workingHours) {
        if(workingHour.workingHour.date.isEqual(other.workingHour.date)){
          sameDay.push(other)
        } else if(other.workingHour.date.isBefore(workingHour.workingHour.date)){
          break;
        }
      }

      daySortWrapper = new DaySortWrapper(sameDay[0].workingHour.date);
      for (const workingHourManagementWorkingHour of sameDay) {
        daySortWrapper.addWorkingHour(workingHourManagementWorkingHour);
      }

      if(weekSortWrapper.dayInWeek.isSameWeek(daySortWrapper.day)){
        weekSortWrapper.addDay(daySortWrapper);
      } else {
        sortWrapper.addWeek(weekSortWrapper);
        weekSortWrapper = new WeekSortWrapper(daySortWrapper.day);
        weekSortWrapper.addDay(daySortWrapper);
      }
    }

    sortWrapper.addWeek(weekSortWrapper);

    this.sortedWorkingHours = sortWrapper;
    this.loadCustomerAndProjects(workingHours);
  }

  getProject(projectId: number): Project | undefined{
    return this.projects.get(projectId);
  }

  getCustomer(customerId: number): Customer | undefined{
    return this.customers.get(customerId);
  }

  private loadCustomerAndProjects(workingHours: WorkingHourManagementWorkingHour[]): void{
    for (const workingHour of workingHours) {
      let customerId = workingHour.workingHour.customerId;
      if(!this.customers.has(customerId)){
        this.customerService.getCustomer(customerId, customer => this.customers.set(customerId, customer))
      }

      let projectId = workingHour.workingHour.projectId;
      if(!this.projects.has(projectId)){
        this.projectService.getProject(projectId, project => this.projects.set(projectId, project))
      }
    }
  }
}


class WorkingHourSortWrapper {

  private _sortedByWeeks: WeekSortWrapper[] = [];


  get sortedByWeeks(): WeekSortWrapper[] {
    return this._sortedByWeeks;
  }

  addWeek(week: WeekSortWrapper): void{
    this._sortedByWeeks.push(week);
  }
}

class WeekSortWrapper {

  private _dayInWeek: CDate;
  private _sortedByDays: DaySortWrapper[] = [];


  constructor(dayInWeek: CDate) {
    this._dayInWeek = dayInWeek;
  }


  get sortedByDays(): DaySortWrapper[] {
    return this._sortedByDays;
  }

  get dayInWeek(): CDate {
    return this._dayInWeek;
  }

  set dayInWeek(value: CDate) {
    this._dayInWeek = value;
  }

  addDay(days: DaySortWrapper): void {
    this._sortedByDays.push(days);
  }
}

class DaySortWrapper {

  private _day: CDate;
  private _sortedByEndTime: WorkingHourManagementWorkingHour[] = [];

  constructor(day: CDate) {
    this._day = day;
  }

  get sortedByEndTime(): WorkingHourManagementWorkingHour[] {
    return this._sortedByEndTime;
  }

  get day(): CDate {
    return this._day;
  }

  set day(value: CDate) {
    this._day = value;
  }

  addWorkingHour(workingHour: WorkingHourManagementWorkingHour): void{
    this._sortedByEndTime.push(workingHour);
  }
}
