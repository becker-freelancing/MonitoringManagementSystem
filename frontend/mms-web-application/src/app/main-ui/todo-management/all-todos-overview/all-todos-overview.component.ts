import {DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, Output} from '@angular/core';
import {Todo} from "../../../../model/todo/todo";
import {TodoManagementTodo} from "../../../../model/todo/todoManagementTodo";
import {DateTime} from "../../../../model/util/DateTime";
import {TodoService} from "../../../../services/todo/todoService";

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

  constructor(todoManagementService: TodoService) {
    todoManagementService.getAllTodos((recTodos: Todo[]) => {

      for (let todo of recTodos) {
        this.todos.push(new TodoManagementTodo(1, todo));
      }

      this.sortTodos();
    })
  }

  public onTodoDeleted(deleted: TodoManagementTodo) {
    this.currentlySelectedUiId = -1;
    this.todos.splice(deleted.uiId - 1, 1);
    this.sortTodos();
  }

  public onTodoEdited(edited: TodoManagementTodo) {
    this.todos[edited.uiId - 1] = edited;
    this.sortTodos();
  }

  public onTodoAdded(added: Todo) {
    let todoManagementTodo = new TodoManagementTodo(this.todos.length + 1, added);
    this.todos.push(todoManagementTodo);
    this.sortTodos();
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

  rearrangeTodos(){
    let uiId = 1;
    for (let todo of this.todos) {
      todo.uiId = uiId;
      uiId++;
    }
  }

  sortTodos(){
    let closedTodos = this.todos.filter(todo => todo.todo.isClosed());
    let unclosedTodosWithEndTime = this.todos.filter(todo => !todo.todo.isClosed() && todo.todo.endTime);
    let unclosedTodosWithoutEndTime = this.todos.filter(todo => !todo.todo.isClosed() && !todo.todo.endTime);

    closedTodos = closedTodos.sort((a, b) => {
      if(a.todo.closedTime && b.todo.closedTime) {
        return b.todo.closedTime.getTime() - a.todo.closedTime.getTime();
      }
      return b.todo.creationTime.getTime() - a.todo.creationTime.getTime();
    })

    unclosedTodosWithEndTime = unclosedTodosWithEndTime.sort((a, b) => {
      if(a.todo.endTime && b.todo.endTime) {
        return a.todo.endTime.getTime() - b.todo.endTime.getTime();
      }
      return a.todo.creationTime.getTime() - b.todo.creationTime.getTime();
    })

    unclosedTodosWithoutEndTime = unclosedTodosWithoutEndTime.sort((a, b) => {
      return a.todo.creationTime.getTime() - b.todo.creationTime.getTime();
    })

    this.todos = unclosedTodosWithEndTime.concat(unclosedTodosWithoutEndTime).concat(closedTodos);
    this.rearrangeTodos();
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
