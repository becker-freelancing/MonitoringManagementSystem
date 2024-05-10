import {Injectable} from "@angular/core";
import {Todo} from "../../model/todo/todo";
import {TodoManagementTodo} from "../../model/todo/todoManagementTodo";

@Injectable({
  providedIn: 'root'
})
export class TodoSyncService {

  static addTodoObserver: ((todos: TodoManagementTodo[]) => void)[] = [];
  static editTodoObserver: ((todos: TodoManagementTodo[]) => void)[] = [];
  static deleteTodoObserver: ((todos: TodoManagementTodo[]) => void)[] = [];

  static todos: TodoManagementTodo[] = [];

  addAddTodoSubscriber(subscriber: (todos: TodoManagementTodo[]) => void): void{
    TodoSyncService.addTodoObserver.push(subscriber);
  }

  addEditTodoSubscriber(subscriber: (todos: TodoManagementTodo[]) => void): void{
    TodoSyncService.editTodoObserver.push(subscriber);
  }

  addDeleteTodoSubscriber(subscriber: (todos: TodoManagementTodo[]) => void): void{
    TodoSyncService.deleteTodoObserver.push(subscriber);
  }

  addTodo(todo: Todo){
    TodoSyncService.todos.push(new TodoManagementTodo(TodoSyncService.todos.length + 1, todo));
    this.sortTodos();
    for (const addTodoObserverElement of TodoSyncService.addTodoObserver) {
      addTodoObserverElement(TodoSyncService.todos);
    }
  }

  editTodo(todo: TodoManagementTodo){
    TodoSyncService.todos[this.getIndex(todo)] = todo;
    this.sortTodos();
    for (const editTodoObserverElement of TodoSyncService.editTodoObserver) {
      editTodoObserverElement(TodoSyncService.todos);
    }
  }

  deleteTodo(todo: TodoManagementTodo){
    TodoSyncService.todos.splice(this.getIndex(todo), 1);
    this.sortTodos();
    for (const deleteTodoObserverElement of TodoSyncService.deleteTodoObserver) {
      deleteTodoObserverElement(TodoSyncService.todos);
    }
  }

  private getIndex(todo: TodoManagementTodo): number {
    let index = 0;
    for (const syncedTodo of TodoSyncService.todos) {
      if (syncedTodo.todo.todoId === todo.todo.todoId){
        return index;
      }
      index++;
    }

    return -1;
  }

  private rearrangeTodos(){
    let uiId = 1;
    for (let todo of TodoSyncService.todos) {
      todo.uiId = uiId;
      uiId++;
    }
  }

  private sortTodos(){
    let closedTodos = TodoSyncService.todos.filter(todo => todo.todo.isClosed());
    let unclosedTodosWithEndTime = TodoSyncService.todos.filter(todo => !todo.todo.isClosed() && todo.todo.endTime);
    let unclosedTodosWithoutEndTime = TodoSyncService.todos.filter(todo => !todo.todo.isClosed() && !todo.todo.endTime);

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

    TodoSyncService.todos = unclosedTodosWithEndTime.concat(unclosedTodosWithoutEndTime).concat(closedTodos);
    this.rearrangeTodos();
  }
}
