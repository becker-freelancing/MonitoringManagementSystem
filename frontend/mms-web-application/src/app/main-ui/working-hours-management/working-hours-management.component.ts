import { Component } from '@angular/core';
import {AddWorkingHourMenuBarComponent} from "./add-working-hour-menu-bar/add-working-hour-menu-bar.component";
import {AllWorkingHoursOverviewComponent} from "./all-working-hours-overview/all-working-hours-overview.component";

@Component({
  selector: 'app-working-hours-management',
  standalone: true,
  imports: [
    AllWorkingHoursOverviewComponent,
    AddWorkingHourMenuBarComponent
  ],
  templateUrl: './working-hours-management.component.html',
  styleUrl: './working-hours-management.component.css'
})
export class WorkingHoursManagementComponent {

}
