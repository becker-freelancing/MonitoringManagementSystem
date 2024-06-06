import {JsonPipe} from "@angular/common";
import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {BarChartModule} from "@swimlane/ngx-charts";
import {Customer} from "../../../../../model/cutomer/customer";
import {CDate} from "../../../../../model/util/cDate";
import {WorkingHoursChartData} from "../working-hours-overview.component";

@Component({
  selector: 'app-working-hours-overview-chart',
  standalone: true,
  imports: [
    BarChartModule,
    JsonPipe
  ],
  templateUrl: './working-hours-overview-chart.component.html',
  styleUrl: './working-hours-overview-chart.component.css'
})
export class WorkingHoursOverviewChartComponent implements OnChanges {

  @Input() workingHours!: WorkingHoursChartData[];
  @Input() startDate!: CDate;
  @Input() endDate!: CDate;
  @Input() customers!: Map<number, Customer>;

  chartData: any[] = [];

  ngOnChanges(changes: SimpleChanges): void {
    this.updateChart();
  }

  private updateChart() {

    if (this.workingHours.length === 0) {
      return;
    }
    let data: any[] = [];

    for (const workingHourGroupedBySeries of this.workingHours) {

      let workingHoursSumByCustomer = new Map<number, number>();

      for (const workingHour of workingHourGroupedBySeries.workingHours) {
        let durationSum = workingHoursSumByCustomer.get(workingHour.workingHour.customerId);
        if (durationSum === undefined) {
          durationSum = 0;
        }
        let duration = workingHour.workingHour.startTime.calcAbsoluteDurationTo(workingHour.workingHour.endTime);
        durationSum += duration.hours * 60 + duration.minutes;
        workingHoursSumByCustomer.set(workingHour.workingHour.customerId, durationSum);
      }

      let seriesData: { name: string, value: number }[] = [];

      for (const entry of workingHoursSumByCustomer.entries()) {
        let customer = this.customers.get(entry[0]);

        if (!customer) {
          continue;
        }

        let hours = Math.floor(entry[1] / 60);
        let minutes = Math.round((entry[1] % 60) / 60 * 100) / 100;
        let value = hours + minutes;
        seriesData.push({name: customer.companyName, value: value})
      }

      data.push({name: workingHourGroupedBySeries.seriesLabel, series: seriesData});
    }

    this.chartData = data;
  }

  view: [number, number] = [700, 400];

  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = false;
  showYAxisLabel = true;
  yAxisLabel = 'Arbeitszeit in Stunden';
  legendTitle = 'Kunde';

  colorScheme: any = {
    domain: [
      '#336e7b',
      '#82b8d1',
      '#1a4c5c',
      '#89c9e8',
      '#4d809a',
      '#a1c5d6',
      '#528ca5',
      '#2a657a',
      '#aacde2',
      '#3d778f',
      '#507f94',
      '#bdd7e3',
      '#5a8c9e',
      '#69a3bb',
      '#7196a7',
      '#597d89',
      '#739eb8',
      '#305e6f',
      '#8fb5c8',
      '#4b6979',
      '#b0ccd7',
      '#779ba5',
      '#577e8f',
      '#6791a1',
      '#829eb0',
      '#497487',
      '#9db7c5',
      '#3c6c7e',
      '#6a94a4',
      '#a8c3d0'
    ]
  };

  constructor() {
    Object.assign(this, {multi: this.chartData});
  }

  protected readonly Number = Number;
}
