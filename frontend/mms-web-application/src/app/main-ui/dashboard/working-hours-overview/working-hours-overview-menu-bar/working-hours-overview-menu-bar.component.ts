import {AfterViewChecked, Component, EventEmitter, OnChanges, Output, SimpleChanges} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {DateRange} from "@angular/material/datepicker";
import {CDate} from "../../../../../model/util/cDate";
import {WorkingHourDateRange, WorkingHourDateRangeUtil, WorkingHoursChartViewMode} from "../workingHoursChartViewMode";

@Component({
  selector: 'app-working-hours-overview-menu-bar',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './working-hours-overview-menu-bar.component.html',
  styleUrl: './working-hours-overview-menu-bar.component.css'
})
export class WorkingHoursOverviewMenuBarComponent{

  @Output("viewModeChange") viewModeEventEmitter = new EventEmitter<WorkingHoursChartViewMode>();

  viewModeMapping = new Map<string, WorkingHoursChartViewMode>();

  constructor() {
    this.viewModeMapping.set("Woche", WorkingHoursChartViewMode.WEEK);
    this.viewModeMapping.set("Monat", WorkingHoursChartViewMode.MONTH);
    this.viewModeMapping.set("Quartal", WorkingHoursChartViewMode.QUARTER);
    this.viewModeMapping.set("Jahr", WorkingHoursChartViewMode.YEAR);
  }

  onWorkingHourOverviewChartViewModeChanged(chartDateRange: any) {
    let dateRange = this.viewModeMapping.get(chartDateRange.target.value);
    this.viewModeEventEmitter.emit(dateRange);
  }

}

