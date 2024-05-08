import {DateTime} from "../util/DateTime";

export class Project{

  projectId?: number;
  title: string;
  shortDescription?: string;
  longDescription?: string;

  creationTime: DateTime;
  startTime?: DateTime;
  endTime?: DateTime;
  closedTime?: DateTime;

  customerId?: number;


  constructor(title: string, creationTime: DateTime, projectId?: number, shortDescription?: string, longDescription?: string, startTime?: DateTime, endTime?: DateTime, closedTime?: DateTime, customerId?: number) {
    this.projectId = projectId;
    this.title = title;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.creationTime = creationTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.closedTime = closedTime;
    this.customerId = customerId;
  }

  isActive() {
    let now = new DateTime();
    if(this.startTime && this.closedTime){
      return this.startTime < now && now < this.closedTime;
    }
    if(this.startTime && this.endTime){
      return this.startTime < now && now < this.endTime;
    }
    if(this.startTime){
      return this.startTime < now;
    }
    return false;
  }
}
