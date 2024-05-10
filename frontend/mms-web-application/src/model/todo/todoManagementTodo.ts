import {Customer} from "../cutomer/customer";

import {CustomerManagementService} from "../../services/customermanagement/customerManagementService";
import {Todo} from "./todo";

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
