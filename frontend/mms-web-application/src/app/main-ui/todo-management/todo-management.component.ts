import {Component, ViewChild} from '@angular/core';
import {Todo} from "../../../model/todo/todo";
import {TodoManagementTodo} from "../../../model/todo/todoManagementTodo";
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

  @ViewChild("allTodosOverview") allTodosOverview?: AllTodosOverviewComponent;
  @ViewChild("todoMenuBar") todoMenuBar?: TodoManagementMenuBarComponent;
  currentlySelectedTodo: TodoManagementTodo | null = null;

  onTodoSelectionChanged(todo: TodoManagementTodo) {
    this.currentlySelectedTodo = todo;
  }

  onTodoDblClicked($event: TodoManagementTodo) {
    this.todoMenuBar?.openEditTodoDialog($event);
  }

  onCloseTodo($event: TodoManagementTodo) {
    this.todoMenuBar?.closeTodo($event);
  }
}
