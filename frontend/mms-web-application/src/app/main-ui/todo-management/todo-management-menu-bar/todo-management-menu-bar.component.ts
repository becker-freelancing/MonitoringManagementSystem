import {NgClass} from "@angular/common";
import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {Todo} from "../../../../model/todo/todo";
import {TodoService} from "../../../../services/todo/todoService";
import {ConfirmDialogService} from "../../../util/confirm-dialog/confirm-dialog.service";
import {TodoManagementTodo} from "../todoManagementTodo";
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
  @Output('todoAdded') todoAdded = new EventEmitter<Todo>();
  @Output('todoEdited') todoEdited = new EventEmitter<TodoManagementTodo>();
  @Output('todoDeleted') todoDeleted = new EventEmitter<TodoManagementTodo>();

  todoService: TodoService;
  editButtonClass: string = 'disabled-button';

  constructor(
    public dialog: MatDialog,
    public confirmDialogService: ConfirmDialogService,
    projectManagementService: TodoService) {
    this.todoService = projectManagementService;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["currentlySelectedTodo"]){
      let newProject = changes['currentlySelectedTodo'].currentValue;
      if(newProject != null){
        this.editButtonClass = 'secondary-button';
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
        this.todoAdded.emit(todo)
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
          this.todoEdited.emit(todo);
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
      this.todoDeleted.emit(todo);
    }), () => {});
  }

  closeTodo(todo: TodoManagementTodo) {
    this.todoService.closeTodo(todo.todo, (closedTodo: Todo) => {todo.todo = closedTodo})
  }
}
