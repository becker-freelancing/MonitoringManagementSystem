import {Injectable} from "@angular/core";
import {Customer} from "../../model/cutomer/customer";
import {Todo} from "../../model/todo/todo";

@Injectable({
  providedIn: 'root',
})
export class TodoService {

  deleteTodosForCustomer(customer: Customer, onSuccess: (todos: Todo[]) => void, onError: (status: number) => void) : void {

  }

}
