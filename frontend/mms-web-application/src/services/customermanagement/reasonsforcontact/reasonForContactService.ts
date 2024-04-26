import {Injectable} from "@angular/core";
import {ContactPersonPosition} from "../../../model/cutomer/contactPersonPosition";
import {ReasonForContact} from "../../../model/cutomer/reasonForContact";
import {HttpClient} from "../../http/httpClient";

@Injectable({
  providedIn: 'root',
})
export class ReasonForContactService{

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  getReasons(onSuccess: (reasons: ReasonForContact[]) => ReasonForContact[], onError?: (status: number) => void): void{
    this.httpClient.get('customers/contactpersons/reasonforcontact/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responsePositions: ReasonForContact[] = [];

      for (let dataItem of r.data){
        responsePositions.push(this.mapToReason(dataItem));
      }
      onSuccess(responsePositions)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }


  getReason(reason: string): ReasonForContact | null{
    return null;
  }


  deleteReason(reason: string): ReasonForContact | null{
    return null;
  }


  saveReason(reason: string, description: string): ReasonForContact | null{
    return null;
  }

  private mapToReason(dataItem: ReasonForContactResponseData): ReasonForContact {

    return new ReasonForContact(dataItem.reason, dataItem.description === null ? undefined : dataItem.description);
  }
}

export interface ReasonForContactResponseData {
  reason: string;
  description: string | null;
}
