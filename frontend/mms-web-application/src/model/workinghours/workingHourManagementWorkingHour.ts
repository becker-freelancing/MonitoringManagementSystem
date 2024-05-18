import {WorkingHour} from "./workingHour";

export class WorkingHourManagementWorkingHour{
  uiId: number;
  workingHour: WorkingHour;


  constructor(uiId: number, workingHour: WorkingHour) {
    this.uiId = uiId;
    this.workingHour = workingHour;
  }
}
