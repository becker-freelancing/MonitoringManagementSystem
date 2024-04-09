import { Component } from '@angular/core';
import {CustomerOverviewComponent} from "./customer-overview/customer-overview.component";
import {IncomeOverviewComponent} from "./income-overview/income-overview.component";
import {TodoOverviewComponent} from "./todo-overview/todo-overview.component";
import {WorkingHoursOverviewComponent} from "./working-hours-overview/working-hours-overview.component";

@Component({
  selector: 'dashboard',
  standalone: true,
  imports: [
    CustomerOverviewComponent,
    IncomeOverviewComponent,
    WorkingHoursOverviewComponent,
    TodoOverviewComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
