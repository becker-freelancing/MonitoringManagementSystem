import {Injectable} from "@angular/core";
import {CDate} from "../../model/util/cDate";
import {DateTime} from "../../model/util/dateTime";
import {Time} from "../../model/util/time";
import {WorkingHour} from "../../model/workinghours/workingHour";
import {HttpClient} from "../http/httpClient";

@Injectable({
  providedIn: 'root',
})
export class WorkingHourService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }


  save(save: WorkingHour, onSuccess: (workingHours: WorkingHour) => void, onError?: (status: number) => void) {

    this.httpClient.post('workinghours/save', save).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      onSuccess(this.mapToWorkingHour(r.data))
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  findOpenForCustomer(customerId: number, onOpenFound: (workingHours: WorkingHour) => void, onNoOpenFound: () => void, onError?: (status: number) => void) {

    this.httpClient.get('workinghours/open/customer/' + customerId).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      if (r.data){
        onOpenFound(this.mapToWorkingHour(r.data));
      } else {
        onNoOpenFound();
      }
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  findAnyOpen(onOpenFound: (workingHours: WorkingHour) => void, onNoOpenFound: () => void, onError?: (status: number) => void) {

    this.httpClient.get('workinghours/open/any').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      if (r.data){
        onOpenFound(this.mapToWorkingHour(r.data));
      } else {
        onNoOpenFound();
      }
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  getForId(id: number, onSuccess: (workingHours: WorkingHour) => void, onError?: (status: number) => void) {

    this.httpClient.get('workinghours/get/' + id).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      onSuccess(this.mapToWorkingHour(r.data))
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  getAll(onSuccess: (workingHours: WorkingHour[]) => void, onError?: (status: number) => void) {

    this.httpClient.get('workinghours/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let workingHours: WorkingHour[] = [];

      for (let dataItem of r.data){
        workingHours.push(this.mapToWorkingHour(dataItem));
      }

      onSuccess(workingHours);
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  delete(id: number, onSuccess: (workingHours: WorkingHour) => void, onError?: (status: number) => void) {

    this.httpClient.delete('workinghours/delete', id).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      onSuccess(this.mapToWorkingHour(r.data))
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  private mapToWorkingHour(dataItem: WorkingHourResponseData): WorkingHour {

    return new WorkingHour(
      CDate.fromDate(dataItem.date),
      Time.fromDate(dataItem.startTime),
      dataItem.customerId,
      dataItem.projectId,
      Time.fromDate(dataItem.endTime),
      dataItem.id
    )
  }

}

interface WorkingHourResponseData {
  id: number;
  date: Date,
  startTime: Date;
  endTime: Date;
  customerId: number;
  projectId: number;
}


