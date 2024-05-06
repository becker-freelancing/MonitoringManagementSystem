import {Customer} from "../cutomer/customer";
import {TodoCategory} from "./todoCategory";

export class Todo{

  todoId: number;
  title: string;
  creationTime: Date;

  shortDescription?: string;
  longDescription?: string;
  endTime?: Date;
  closedTime?: Date;
  customerId?: number;
  category?: TodoCategory;


  constructor(todoId: number, title: string, creationTime: Date, shortDescription: string, longDescription: string, endTime: Date, closedTime: Date, customerId: number, category: TodoCategory) {
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
}
