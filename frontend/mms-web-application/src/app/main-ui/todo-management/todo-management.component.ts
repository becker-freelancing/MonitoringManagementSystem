import { Component } from '@angular/core';
import {AllTodosOverviewComponent} from "./all-todos-overview/all-todos-overview.component";
import {TodoManagementFilterBarComponent} from "./todo-management-filter-bar/todo-management-filter-bar.component";
import {TodoManagementMenuBarComponent} from "./todo-management-menu-bar/todo-management-menu-bar.component";

@Component({
  selector: 'app-todo-management',
  standalone: true,
  imports: [
    TodoManagementMenuBarComponent,
    TodoManagementFilterBarComponent,
    AllTodosOverviewComponent
  ],
  templateUrl: './todo-management.component.html',
  styleUrl: './todo-management.component.css'
})
export class TodoManagementComponent {

}
