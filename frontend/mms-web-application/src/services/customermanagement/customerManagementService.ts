import {Injectable} from "@angular/core";
import {Address} from "../../model/cutomer/address";
import {ContactPerson} from "../../model/cutomer/contactPerson";
import {Customer} from "../../model/cutomer/customer";
import {DefaultErrorDialog} from "../http/defaultErrorDialog";
import {HttpClient} from "../http/httpClient";

@Injectable({
    providedIn: 'root',
  })
export class CustomerManagementService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  saveCustomer(customer: Customer, onSuccess?: (customer: Customer) => void, onError?: (status: number) => void): Customer | null {

    this.httpClient.post('customers/save', customer).then(r => {
      if (r.status == 200){
        return null;
      }

      return r.data;
    }).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      if (onSuccess) {
        onSuccess(this.mapToCustomer(r.data))
      }
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
    return null;
  }

  updateCustomer(customer: Customer): Customer | null {
    return null;
  }

  deleteCustomer(customer: Customer, onSuccess: (customer: Customer) => void, onError: (status: number) => void): void{
    if(customer.customerId == -1){
      return;
    }
    this.httpClient.delete('customers/delete', customer.customerId).then(r =>{
      if (r.status != 200){
        onError(r.status);
        return;
      }
      onSuccess(this.mapToCustomer(r.data))
      }).catch(error => {onError(error.status)})
  }

  getCustomer(customer: Customer): Customer | null {
    return null;
  }

  getAllCustomers(accept: (customers: Customer[]) => void, onError: (status: number) => void): void{
    this.httpClient.get('customers/get').then(r => {
      if (r.status != 200){
        onError(r.status);
        return;
      }

      let responseCustomers: Customer[] = [];

      for (let dataItem of r.data){
        responseCustomers.push(this.mapToCustomer(dataItem));
      }
      accept(responseCustomers)
    }).catch((reason: any) => {
      onError(reason.status)
    })
  }

  mapToCustomer(data: CustomerResponseData): Customer{

    let id: number = data['id'];
    let compName: string = data['companyName'];
    let address: Address | undefined = this.mapAddress(data['address']);
    let logo: Object[] | null = data['logo'];
    let contactPersons: ContactPerson[] = this.mapContactPersons(data['contactPersons']);

    return new Customer(compName, address, contactPersons, id);
  }

  mapAddress(data: AddressResponseData | null): Address | undefined{
    if(data == null){
      return undefined;
    }

    return undefined;
  }

  private mapContactPersons(datum: ContactPersonResponseData[]) {
    return [];
  }
}

export interface CustomerResponseData {
  id: number;
  companyName: string;
  address: Object | null;
  contactPersons: Object[];
  logo: Object[] | null;
}

export interface AddressResponseData {

}

export interface ContactPersonResponseData {

}
