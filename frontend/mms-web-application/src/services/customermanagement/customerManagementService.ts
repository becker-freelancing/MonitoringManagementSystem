import {Injectable} from "@angular/core";
import {Customer} from "../../model/cutomer/customer";
import {HttpClient} from "../http/httpClient";

@Injectable({
    providedIn: 'root',
  })
export class CustomerManagementService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  addCustomer(customer: Customer): Customer | null {

    console.log(customer)
    this.httpClient.post('customers/save', customer).then(r => {
      if (r.status == 200){
        return null;
      }

      return r.data;
    })
    return null;
  }

  updateCustomer(customer: Customer): Customer | null {
    return null;
  }

  deleteCustomer(customer: Customer): Customer | null {
    return null;
  }

  getCustomer(customer: Customer): Customer | null {
    return null;
  }
}
