export class Project{

  projectId?: number;
  title: string;
  shortDescription?: string;
  longDescription?: string;

  creationTime: Date;
  startTime?: Date;
  endTime?: Date;

  customerId?: number;


  constructor(title: string, creationTime: Date, projectId?: number, shortDescription?: string, longDescription?: string, startTime?: Date, endTime?: Date, customerId?: number) {
    this.projectId = projectId;
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
