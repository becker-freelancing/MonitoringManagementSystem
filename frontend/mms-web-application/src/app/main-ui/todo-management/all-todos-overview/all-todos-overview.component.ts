import {DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, Output} from '@angular/core';
import {Todo} from "../../../../model/todo/todo";
import {TodoService} from "../../../../services/todo/todoService";
import {TodoManagementTodo} from "../todoManagementTodo";

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
      let uiId = 1;

      for (let todo of recTodos) {
        this.todos.push(new TodoManagementTodo(uiId, todo));
        uiId++;
      }
    })
  }

  public onTodoDeleted(deleted: TodoManagementTodo) {
    this.currentlySelectedUiId = -1;
    this.todos.splice(deleted.uiId - 1, 1);
    let uiId = 1;
    for (let todo of this.todos) {
      todo.uiId = uiId;
      uiId++;
    }
  }

  public onTodoEdited(edited: TodoManagementTodo) {
    this.todos[edited.uiId - 1] = edited;
  }

  public onTodoAdded(added: Todo) {
    let todoManagementTodo = new TodoManagementTodo(this.todos.length + 1, added);
    this.todos.push(todoManagementTodo);
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
}
