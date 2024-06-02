import {Injectable} from "@angular/core";
import {WorkingHour} from "../../model/workinghours/workingHour";
import {WorkingHourManagementWorkingHour} from "../../model/workinghours/workingHourManagementWorkingHour";

@Injectable({
  providedIn: 'root'
})
export class WorkingHourSyncService{


  static addWorkingHourObserver: ((workingHours: WorkingHourManagementWorkingHour[]) => void)[] = [];
  static editWorkingHourObserver: ((workingHours: WorkingHourManagementWorkingHour[]) => void)[] = [];
  static deleteWorkingHourObserver: ((workingHours: WorkingHourManagementWorkingHour[]) => void)[] = [];

  static workingHours: WorkingHourManagementWorkingHour[] = [];

  addAddWorkingHourSubscriber(subscriber: (workingHours: WorkingHourManagementWorkingHour[]) => void): void{
    WorkingHourSyncService.addWorkingHourObserver.push(subscriber);
  }

  addEditWorkingHourSubscriber(subscriber: (workingHours: WorkingHourManagementWorkingHour[]) => void): void{
    WorkingHourSyncService.editWorkingHourObserver.push(subscriber);
  }

  addDeleteWorkingHourSubscriber(subscriber: (workingHours: WorkingHourManagementWorkingHour[]) => void): void{
    WorkingHourSyncService.deleteWorkingHourObserver.push(subscriber);
  }

  clearWorkingHours(){
    WorkingHourSyncService.workingHours = [];
  }

  addWorkingHour(workingHour: WorkingHour){
    WorkingHourSyncService.workingHours.push(new WorkingHourManagementWorkingHour(WorkingHourSyncService.workingHours.length + 1, workingHour));
    this.sortWorkingHours();
    for (const addWorkingHourObserverElement of WorkingHourSyncService.addWorkingHourObserver) {
      addWorkingHourObserverElement(WorkingHourSyncService.workingHours);
    }
  }

  setWorkingHours(workingHours: WorkingHour[]){
    this.clearWorkingHours();
    for (const workingHour of workingHours) {
      WorkingHourSyncService.workingHours.push(new WorkingHourManagementWorkingHour(WorkingHourSyncService.workingHours.length + 1, workingHour));
    }
    this.sortWorkingHours();
    for (const addWorkingHourObserverElement of WorkingHourSyncService.addWorkingHourObserver) {
      addWorkingHourObserverElement(WorkingHourSyncService.workingHours);
    }
  }

  editWorkingHour(workingHour: WorkingHourManagementWorkingHour){
    WorkingHourSyncService.workingHours[this.getIndex(workingHour)] = workingHour;
    this.sortWorkingHours();
    for (const editWorkingHourObserverElement of WorkingHourSyncService.editWorkingHourObserver) {
      editWorkingHourObserverElement(WorkingHourSyncService.workingHours);
    }
  }

  deleteWorkingHour(workingHour: WorkingHourManagementWorkingHour){
    WorkingHourSyncService.workingHours.splice(this.getIndex(workingHour), 1);
    this.sortWorkingHours();
    for (const deleteWorkingHourObserverElement of WorkingHourSyncService.deleteWorkingHourObserver) {
      deleteWorkingHourObserverElement(WorkingHourSyncService.workingHours);
    }
  }

  private getIndex(workingHour: WorkingHourManagementWorkingHour): number {
    let index = 0;
    for (const syncedWorkingHour of WorkingHourSyncService.workingHours) {
      if (syncedWorkingHour.workingHour.id === workingHour.workingHour.id){
        return index;
      }
      index++;
    }

    return -1;
  }

  private rearrangeWorkingHours(){
    let uiId = 1;
    for (let workingHour of WorkingHourSyncService.workingHours) {
      workingHour.uiId = uiId;
      uiId++;
    }
  }

  private sortWorkingHours(){
    let closedWorkingHours = WorkingHourSyncService.workingHours.filter(workingHour => workingHour.workingHour.isClosed());
    let unclosedWorkingHours = WorkingHourSyncService.workingHours.filter(workingHour => !workingHour.workingHour.isClosed());

    closedWorkingHours = closedWorkingHours.sort((a, b) => {
      let dateCompare = b.workingHour.date.getTime() - a.workingHour.date.getTime();
      if(dateCompare !== 0){
        return dateCompare;
      }

      if(a.workingHour.endTime && b.workingHour.endTime) {
        return b.workingHour.endTime.getTime() - a.workingHour.endTime.getTime();
      }
      return b.workingHour.startTime.getTime() - a.workingHour.startTime.getTime();
    })

    unclosedWorkingHours = unclosedWorkingHours.sort((a, b) => {
      let dateCompare = b.workingHour.date.getTime() - a.workingHour.date.getTime();
      if(dateCompare !== 0){
        return dateCompare;
      }

      return a.workingHour.startTime.getTime() - b.workingHour.startTime.getTime();
    })


    WorkingHourSyncService.workingHours = unclosedWorkingHours.concat(closedWorkingHours)
    this.rearrangeWorkingHours();
  }
}
