import {DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, Output} from '@angular/core';
import {Todo} from "../../../../model/todo/todo";
import {TodoManagementTodo} from "../../../../model/todo/todoManagementTodo";
import {DateTime} from "../../../../model/util/dateTime";
import {TodoService} from "../../../../services/todo/todoService";
import {TodoSyncService} from "../../../../services/todo/todoSyncService";

@Component({
  selector: 'app-all-todos-overview',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    DatePipe,
    NgClass
  ],
  templateUrl: './all-todos-overview.component.html',
  styleUrl: './all-todos-overview.component.css'
})
export class AllTodosOverviewComponent {

  @Output('todoChanged') todoChangeEventEmitter = new EventEmitter<TodoManagementTodo>();
  @Output('todoDblClicked') todoDblChangeEventEmitter = new EventEmitter<TodoManagementTodo>();
  @Output('closeTodo') closeTodoEventEmitter = new EventEmitter<TodoManagementTodo>();

  todos: TodoManagementTodo[] = [];

  currentlySelectedUiId: number = -1;

  constructor(
    todoManagementService: TodoService,
    todoSyncService: TodoSyncService) {


    todoSyncService.addAddTodoSubscriber((changed) => this.todos = changed);
    todoSyncService.addEditTodoSubscriber((changed) => {
      this.todos = changed
      this.currentlySelectedUiId = -1;
      this.todoChangeEventEmitter.emit(undefined);
    });
    todoSyncService.addDeleteTodoSubscriber((changed) => {
      this.currentlySelectedUiId = -1;
      this.todos = changed;
    });

    todoManagementService.getAllTodos((recTodos: Todo[]) => {
      for (const recTodo of recTodos) {
        todoSyncService.addTodo(recTodo);
      }
    });
  }

  onTodoClicked(todo: TodoManagementTodo) {
    if (todo.todo.closedTime) {
      return;
    }
    this.currentlySelectedUiId = todo.uiId;
    this.todoChangeEventEmitter.emit(todo);
  }

  onTodoDblClicked(todo: TodoManagementTodo) {
    this.todoDblChangeEventEmitter.emit(todo);
  }

  closeTodo(todo: TodoManagementTodo) {
    this.closeTodoEventEmitter.emit(todo);
  }

  getTodoCardStyle(todo: TodoManagementTodo) {
    let styles: string[] = ['all-todo-overview-card'];
    if(this.currentlySelectedUiId === todo.uiId){
      styles.push('all-todo-overview-card-selected');
    }

    if(todo.todo.isClosed()){
      styles.push('all-todo-overview-card-closed')
    }
    return styles;
  }

  getTodoEndTimeStyle(todo: TodoManagementTodo): string {
      let endTime = todo.todo.endTime;

      if(!endTime || todo.todo.isClosed()){
        return '';
      }

      let now = new DateTime();

      let date = now.getDate();
      let month = now.getMonth();
      let year = now.getUTCFullYear();

      if(endTime.getMonth() == month && endTime.getUTCFullYear() === year){
        if(endTime.getDate() <= date){
          return 'all-todo-overview-card-end-time-high-priority';
        } else if(endTime.getDate() - 1 == date) {
          return 'all-todo-overview-card-end-time-moderate-priority';
        }
      }

      return 'all-todo-overview-card-end-time-low-priority';
  }
}
