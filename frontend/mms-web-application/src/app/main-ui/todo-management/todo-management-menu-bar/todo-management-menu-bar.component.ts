import {NgClass} from "@angular/common";
import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {Todo} from "../../../../model/todo/todo";
import {TodoManagementTodo} from "../../../../model/todo/todoManagementTodo";
import {TodoService} from "../../../../services/todo/todoService";
import {TodoSyncService} from "../../../../services/todo/todoSyncService";
import {ConfirmDialogService} from "../../../util/confirm-dialog/confirm-dialog.service";
import {AddTodoDialogComponent} from "./add-todo-dialog/add-todo-dialog.component";
import {EditTodoDialogComponent} from "./edit-todo-dialog/edit-todo-dialog.component";

@Component({
  selector: 'app-todo-management-menu-bar',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './todo-management-menu-bar.component.html',
  styleUrl: './todo-management-menu-bar.component.css'
})
export class TodoManagementMenuBarComponent implements OnChanges{

  @Input('currentlySelectedTodo') currentlySelectedTodo: TodoManagementTodo | null = null;

  todoService: TodoService;
  editButtonClass: string = 'disabled-button';

  constructor(
    public dialog: MatDialog,
    public confirmDialogService: ConfirmDialogService,
    projectManagementService: TodoService,
    public todoSyncService: TodoSyncService) {
    this.todoService = projectManagementService;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["currentlySelectedTodo"]){
      let newProject = changes['currentlySelectedTodo'].currentValue;
      if(newProject != null){
        this.editButtonClass = 'secondary-button';
      } else {
        this.editButtonClass = 'disabled-button';
      }
    }
  }

  openAddTodoDialog(): void {
    let dialogRef = this.dialog.open(AddTodoDialogComponent, {
      width: '90%',
      height: '90%'
    });

    dialogRef.afterClosed().subscribe(todo => {
      this.todoService.saveTodo(todo, (todo: Todo) => {
        this.todoSyncService.addTodo(todo);
      });
    })

  }

  openEditTodoDialog(todo: TodoManagementTodo | null) {
    if (todo == null){
      return;
    }

    this.dialog.open(EditTodoDialogComponent, {
      width: '90%',
      height: '90%',
      data: todo
    }).afterClosed().subscribe(todo => {
      if(todo === undefined){
        return;
      }
      this.todoService.saveTodo(todo.todo,
        (saved: Todo) => {
          todo.todo = saved;
          this.todoSyncService.editTodo(todo);
        });
    });
  }

  openDeleteTodoDialog(todo: TodoManagementTodo | null) {
    if(todo == null){
      return;
    }

    this.confirmDialogService.showConfirmDeleteDialog(() => this.todoService.deleteTodo(todo.todo, (deleted: Todo) => {
      todo.todo = deleted;
      this.editButtonClass = 'disabled-button';
      this.todoSyncService.deleteTodo(todo);
    }), () => {});
  }

  closeTodo(todo: TodoManagementTodo) {
    this.todoService.closeTodo(todo.todo, (closedTodo: Todo) => {
      todo.todo = closedTodo;
      this.todoSyncService.editTodo(todo)
    })
  }
}
