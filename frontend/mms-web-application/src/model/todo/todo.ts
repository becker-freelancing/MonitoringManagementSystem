import {Customer} from "../cutomer/customer";
import {DateTime} from "../util/DateTime";
import {TodoCategory} from "./todoCategory";

export class Todo{

  todoId?: number;
  title: string;
  creationTime: DateTime;

  shortDescription?: string;
  longDescription?: string;
  endTime?: DateTime;
  closedTime?: DateTime;
  customerId?: number;
  category?: TodoCategory;


  constructor(title: string, creationTime: DateTime, shortDescription?: string, longDescription?: string, endTime?: DateTime, closedTime?: DateTime, customerId?: number, category?: TodoCategory, todoId?: number) {
    this.todoId = todoId;
    this.title = title;
    this.creationTime = creationTime;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.endTime = endTime;
    this.closedTime = closedTime;
    this.customerId = customerId;
    this.category = category;
  }

  isClosed() {
    if(!this.closedTime){
      return false;
    }

    return this.closedTime < new Date();
  }
}
