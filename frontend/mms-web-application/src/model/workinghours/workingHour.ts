import {DateTime} from "../util/dateTime";

export class WorkingHour {
  id?: number;
  startTime: DateTime;
  endTime?: DateTime;
  customerId?: number;


  constructor(startTime: DateTime, endTime?: DateTime, customerId?: number, id?: number) {
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
    this.customerId = customerId;
  }

  isClosed(): boolean {
    return this.endTime != undefined;
  }
}
