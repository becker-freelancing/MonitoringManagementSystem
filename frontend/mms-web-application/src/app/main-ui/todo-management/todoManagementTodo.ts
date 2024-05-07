import {Todo} from "../../../model/todo/todo";

export class TodoManagementTodo {

  uiId: number;
  todo: Todo;

  constructor(uiId: number, todo: Todo) {
    this.uiId = uiId;
    this.todo = todo;
  }
}
