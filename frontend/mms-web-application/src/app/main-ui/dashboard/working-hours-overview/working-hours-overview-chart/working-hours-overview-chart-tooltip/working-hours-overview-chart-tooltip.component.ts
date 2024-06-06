import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-working-hours-overview-chart-tooltip',
  standalone: true,
  imports: [],
  templateUrl: './working-hours-overview-chart-tooltip.component.html',
  styleUrl: './working-hours-overview-chart-tooltip.component.css'
})
export class WorkingHoursOverviewChartTooltipComponent {
  @Input() model: any;

  getTooltipContent(): string {
    if (!this.model) {
      return '';
    }
    const { name, value, series } = this.model;
    return `<strong>${series}</strong><br>${name}: ${value}`;
  }
}
