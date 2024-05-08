import {Customer} from "../../../model/cutomer/customer";
import {Todo} from "../../../model/todo/todo";
import {CustomerManagementService} from "../../../services/customermanagement/customerManagementService";

export class TodoManagementTodo {

  uiId: number;
  todo: Todo;
  customer?: Customer;

  constructor(uiId: number, todo: Todo) {
    this.uiId = uiId;
    this.todo = todo;

    if(todo.customerId){
      new CustomerManagementService().getCustomer(todo.customerId, (customer: Customer) => this.customer = customer);
    }
  }
}
