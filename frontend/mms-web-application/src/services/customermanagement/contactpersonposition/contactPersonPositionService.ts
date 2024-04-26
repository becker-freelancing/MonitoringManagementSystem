import {Injectable} from "@angular/core";
import {ContactPersonPosition} from "../../../model/cutomer/contactPersonPosition";
import {Customer} from "../../../model/cutomer/customer";
import {HttpClient} from "../../http/httpClient";

@Injectable({
  providedIn: 'root',
})
export class ContactPersonPositionService{

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  getPositions(onSuccess: (positions: ContactPersonPosition[]) => void, onError?: (status: number) => void): void{
    this.httpClient.get('customers/contactpersons/positions/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responsePositions: ContactPersonPosition[] = [];

      for (let dataItem of r.data){
        responsePositions.push(this.mapToPosition(dataItem));
      }
      onSuccess(responsePositions)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }


  getPosition(position: string): ContactPersonPosition | null{
    return null;
  }


  deletePosition(position: string): ContactPersonPosition | null{
    return null;
  }


  savePositions(position: string, description: string): ContactPersonPosition | null{
    return null;
  }

  mapToPosition(data: ContactPersonPositionResponseData): ContactPersonPosition{

    return new ContactPersonPosition(data.position, data.description == null ? undefined : data.description);
  }
}

export interface ContactPersonPositionResponseData {
  position: string;
  description: string | null;
}
