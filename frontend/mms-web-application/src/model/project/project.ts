export class Project{

  id?: number;
  title: string;
  shortDescription?: string;
  longDescription?: string;

  creationTime: Date;
  startTime?: Date;
  endTime?: Date;

  customerId?: number;


  constructor(title: string, creationTime: Date, id?: number, shortDescription?: string, longDescription?: string, startTime?: Date, endTime?: Date, customerId?: number) {
    this.id = id;
    this.title = title;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.creationTime = creationTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.customerId = customerId;
  }

  isActive() {
    return false;
  }
}
