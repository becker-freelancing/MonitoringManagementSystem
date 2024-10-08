import {Injectable} from "@angular/core";
import {Customer} from "../../model/cutomer/customer";
import {Todo} from "../../model/todo/todo";
import {DateTime} from "../../model/util/dateTime";
import {HttpClient} from "../http/httpClient";
import {TodoCategoryResponseData, TodoCategoryService} from "./todoCategoryService";

@Injectable({
  providedIn: 'root',
})
export class TodoService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  closeTodo(todo: Todo, onSuccess: (todo: Todo) => void, onError?:(status: number) => void){
    this.httpClient.get('todo/close/' + todo.todoId + '/' + JSON.stringify(new DateTime())).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      onSuccess(this.mapToTodo(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  deleteTodosForCustomer(customer: Customer, onSuccess: (todos: Todo[]) => void, onError: (status: number) => void) : void {
    this.httpClient.delete('todo/delete/customer', customer.customerId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      let responseProjects: Todo[] = [];

      for (let dataItem of r.data){
        responseProjects.push(this.mapToTodo(dataItem));
      }
      onSuccess(responseProjects);
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  deleteTodo(todo: Todo, onSuccess: (todo: Todo) => void, onError?: (status: number) => void) {
    this.httpClient.delete('todo/delete', todo.todoId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToTodo(r.data))
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  saveTodo(todo: Todo, onSuccess?: (todo: Todo) => void, onError?: (status: number) => void) {
    this.httpClient.post('todo/save', todo).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      if (onSuccess) {
        onSuccess(this.mapToTodo(r.data))
      }
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  getAllTodos(onSuccess: (todos: Todo[]) => void, onError?: (status: number) => void) {

    this.httpClient.get('todo/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responseTodos: Todo[] = [];

      for (let dataItem of r.data){
        responseTodos.push(this.mapToTodo(dataItem));
      }
      onSuccess(responseTodos)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }


  private mapToTodo(dataItem: TodoResponseData): Todo {

    return new Todo(
      dataItem.title,
      new DateTime(dataItem.creationTime),
      dataItem.shortDescription,
      dataItem.longDescription,
      DateTime.fromDate(dataItem.endTime),
      DateTime.fromDate(dataItem.closedTime),
      dataItem.customerId,
      TodoCategoryService.mapCategoryOrNull(dataItem.category),
      dataItem.todoId
    )
  }

}

interface TodoResponseData {
  todoId: number;
  title: string;
  shortDescription: string;
  longDescription: string;
  creationTime: Date;
  endTime: Date;
  closedTime: Date;
  customerId: number;
  category: TodoCategoryResponseData;
}


