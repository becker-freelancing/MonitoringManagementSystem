import {Component} from '@angular/core';
import {NgxChartsModule} from "@swimlane/ngx-charts";
import {Customer} from "../../../../model/cutomer/customer";
import {CDate} from "../../../../model/util/cDate";
import {WorkingHour} from "../../../../model/workinghours/workingHour";
import {WorkingHourManagementWorkingHour} from "../../../../model/workinghours/workingHourManagementWorkingHour";
import {CustomerManagementService} from "../../../../services/customermanagement/customerManagementService";
import {WorkingHourService} from "../../../../services/workinghourmanagement/workingHourService";
import {WorkingHourSyncService} from "../../../../services/workinghourmanagement/workingHourSyncService";
import {
  WorkingHoursOverviewChartComponent
} from "./working-hours-overview-chart/working-hours-overview-chart.component";
import {
  WorkingHoursOverviewMenuBarComponent
} from "./working-hours-overview-menu-bar/working-hours-overview-menu-bar.component";
import {
  WorkingHourDateRange, WorkingHourDateRangeUtil,
  WorkingHoursChartViewMode,
  WorkingHoursChartViewModeUtil
} from "./workingHoursChartViewMode";

@Component({
  selector: 'working-hours-overview',
  standalone: true,
  imports: [
    NgxChartsModule,
    WorkingHoursOverviewChartComponent,
    WorkingHoursOverviewMenuBarComponent
  ],
  templateUrl: './working-hours-overview.component.html',
  styleUrl: './working-hours-overview.component.css'
})
export class WorkingHoursOverviewComponent {

  workingHours: WorkingHoursChartData[] = [];
  dateRange: WorkingHourDateRange = WorkingHourDateRangeUtil.getDefaultDateRange();
  chartViewMode: WorkingHoursChartViewMode = WorkingHoursChartViewMode.WEEK;
  customers: Map<number, Customer> = new Map<number, Customer>();

  constructor(
    public workingHoursSyncService: WorkingHourSyncService,
    workingHoursService: WorkingHourService,
    customerService: CustomerManagementService
  ) {

    workingHoursSyncService.addAddWorkingHourSubscriber(workingHours => this.update(workingHours))
    workingHoursSyncService.addEditWorkingHourSubscriber(workingHours => this.update(workingHours))
    workingHoursSyncService.addDeleteWorkingHourSubscriber(workingHours => this.update(workingHours))

    let workingHoursBuffer: WorkingHour[] = [];
    let customersRec: boolean = false;
    let workingHoursRec: boolean = false;

    workingHoursService.getAll((workingHours) => {
      workingHoursRec = true;
      workingHoursBuffer = workingHours;
      if (customersRec) {
        workingHoursSyncService.setWorkingHours(workingHours);
      }
    });

    customerService.getAllCustomers(reqCustomers => {
      for (const customer of reqCustomers) {
        this.customers.set(customer.customerId, customer);
      }
      customersRec = true;
      if(workingHoursRec){
        workingHoursSyncService.setWorkingHours(workingHoursBuffer);
      }
    })
  }

  private update(workingHours: WorkingHourManagementWorkingHour[]) {
    let filteredByDate =  this.filterBetweenDatesInclusive(workingHours, this.dateRange.start, this.dateRange.end);
    this.workingHours = this.groupWorkingHours(filteredByDate, this.chartViewMode);
  }

  onWorkingHourViewModeChanged($event: WorkingHoursChartViewMode) {
    this.dateRange = WorkingHoursChartViewModeUtil.getDateTimeRange($event);
    this.chartViewMode = $event;
    this.update(this.workingHoursSyncService.getWorkingHours());
  }

  private filterBetweenDatesInclusive(workingHours: WorkingHourManagementWorkingHour[], start: CDate, end: CDate) {
    return workingHours.filter(workingHour =>
      (workingHour.workingHour.date.isEqual(start) ||
        workingHour.workingHour.date.isEqual(end)) || (
        workingHour.workingHour.date.isBefore(end) &&
        workingHour.workingHour.date.isAfter(start)
      ))
  }

  private groupWorkingHours(workingHours: WorkingHourManagementWorkingHour[], chartViewMode: WorkingHoursChartViewMode): WorkingHoursChartData[] {
    if(workingHours.length === 0){
      return [];
    }
    switch (chartViewMode){
      case WorkingHoursChartViewMode.WEEK: return this.groupForWeek(workingHours);
      case WorkingHoursChartViewMode.MONTH: return this.groupForMonth(workingHours);
      case WorkingHoursChartViewMode.QUARTER: return this.groupForQuarter(workingHours);
      case WorkingHoursChartViewMode.YEAR: return this.groupForYear(workingHours);
    }
  }

  private groupForWeek(workingHours: WorkingHourManagementWorkingHour[]): WorkingHoursChartData[] {
    let res: WorkingHoursChartData[] = [];
    let date: CDate = workingHours[0].workingHour.date.getLastMonday();

    for(let i = 0; i < 7; i++){
      let filteredWorkingHours = this.filterBetweenDatesInclusive(workingHours, date, date);
      let curr: WorkingHoursChartData = new WorkingHoursChartData();
      curr.seriesLabel = date.getDayOfWeekAsString() + ' ' + date.toStringWithoutYear();
      curr.workingHours = filteredWorkingHours;
      res.push(curr);
      date = date.nextDay();
    }

    return res;
  }

  private groupForMonth(workingHours: WorkingHourManagementWorkingHour[]): WorkingHoursChartData[] {
    let res: WorkingHoursChartData[] = [];
    let date: CDate = workingHours[0].workingHour.date.getLastMonthStart();

    for(let i = 0; i < 4; i++){
      let nextSunday = date.getNextSunday();
      let filteredWorkingHours = this.filterBetweenDatesInclusive(workingHours, date, nextSunday);
      let curr: WorkingHoursChartData = new WorkingHoursChartData();
      curr.seriesLabel = date.getDayOfWeekAsString() + ' ' + date.toStringWithoutYear() + ' - ' + nextSunday.getDayOfWeekAsString() + ' ' + nextSunday.toStringWithoutYear();
      curr.workingHours = filteredWorkingHours;
      res.push(curr);
      date = nextSunday.nextDay();
    }

    return res;
  }

  private groupForQuarter(workingHours: WorkingHourManagementWorkingHour[]): WorkingHoursChartData[] {
    let res: WorkingHoursChartData[] = [];
    let date: CDate = workingHours[0].workingHour.date.getLastQuarterStart();

    for(let i = 0; i < 3; i++){
      let lastMonthDate = date.getNextMonthStart().lastDay();
      let filteredWorkingHours = this.filterBetweenDatesInclusive(workingHours, date, lastMonthDate);
      let curr: WorkingHoursChartData = new WorkingHoursChartData();
      curr.seriesLabel = date.getDayOfWeekAsString() + ' ' + date.toStringWithoutYear() + ' - ' + lastMonthDate.getDayOfWeekAsString() + ' ' + lastMonthDate.toStringWithoutYear();
      curr.workingHours = filteredWorkingHours;
      res.push(curr);
      date = lastMonthDate.nextDay();
    }

    return res;
  }

  private groupForYear(workingHours: WorkingHourManagementWorkingHour[]): WorkingHoursChartData[] {
    let res: WorkingHoursChartData[] = [];
    let date: CDate = workingHours[0].workingHour.date.getLastYearStart();

    for(let i = 0; i < 12; i++){
      let lastMonthDate = date.getNextMonthStart().lastDay();
      let filteredWorkingHours = this.filterBetweenDatesInclusive(workingHours, date, lastMonthDate);
      let curr: WorkingHoursChartData = new WorkingHoursChartData();
      curr.seriesLabel = date.getDayOfWeekAsString() + ' ' + date.toStringWithoutYear() + ' - ' + lastMonthDate.getDayOfWeekAsString() + ' ' + lastMonthDate.toStringWithoutYear();
      curr.workingHours = filteredWorkingHours;
      res.push(curr);
      date = lastMonthDate.nextDay();
    }

    return res;
  }
}

export class WorkingHoursChartData {
  workingHours: WorkingHourManagementWorkingHour[] = [];
  seriesLabel: string = '';
}
