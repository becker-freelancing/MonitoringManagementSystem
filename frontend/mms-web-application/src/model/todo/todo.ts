import {Customer} from "../cutomer/customer";

export class Todo{

  topic: string;
  finished: boolean;
  customer?: Customer;
  description: string;
  endDate?: Date;
  creationDate: Date;


  constructor(topic: string, finished: boolean, customer?: Customer, description?: string, endDate?: Date, creationDate?: Date) {
    this.topic = topic;
    this.finished = finished;
    this.customer = customer;
    this.description = description ?? '';
    this.endDate = endDate;
    this.creationDate = creationDate ?? new Date();
  }
}
