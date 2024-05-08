import {DatePipe, NgForOf, NgIf} from "@angular/common";
import { Component } from '@angular/core';
import {Todo} from "../../../../model/todo/todo";
import {TodoService} from "../../../../services/todo/todoService";
import {TodoManagementTodo} from "../todoManagementTodo";

@Component({
  selector: 'app-all-todos-overview',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './all-todos-overview.component.html',
  styleUrl: './all-todos-overview.component.css'
})
export class AllTodosOverviewComponent {

  todos: TodoManagementTodo[] = [];

  constructor(todoManagementService: TodoService) {
    todoManagementService.getAllTodos((recTodos: Todo[]) => {
      let uiId = 1;

      for(let todo of recTodos){
        this.todos.push(new TodoManagementTodo(uiId, todo));
        uiId++;
      }
    })
  }

  public onTodoDeleted(deleted: TodoManagementTodo){
    this.todos.splice(deleted.uiId - 1, 1);
  }

  public onTodoEdited(edited: TodoManagementTodo){
    this.todos[edited.uiId - 1] = edited;
  }

  public onTodoAdded(added: Todo){
    let todoManagementTodo = new TodoManagementTodo(this.todos.length + 1, added);
    this.todos.push(todoManagementTodo);
  }
}
