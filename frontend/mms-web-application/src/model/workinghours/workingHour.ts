import {CDate} from "../util/cDate";
import {Time} from "../util/time";

export class WorkingHour {

  id?: number;
  date: CDate;
  startTime: Time;
  endTime?: Time;
  customerId: number;
  projectId: number;


  constructor(date: CDate, startTime: Time, customerId: number, projectId: number, endTime?: Time, id?: number) {
    this.id = id;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.customerId = customerId;
    this.projectId = projectId;
  }

  isClosed(): boolean {
    return this.endTime != undefined;
  }
}
