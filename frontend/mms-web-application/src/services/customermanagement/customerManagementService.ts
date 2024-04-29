import {Injectable} from "@angular/core";
import {Address} from "../../model/cutomer/address";
import {ContactPerson} from "../../model/cutomer/contactPerson";
import {ContactPersonPosition} from "../../model/cutomer/contactPersonPosition";
import {CountryUtil} from "../../model/cutomer/country";
import {Customer} from "../../model/cutomer/customer";
import {ReasonForContact} from "../../model/cutomer/reasonForContact";
import {DefaultErrorDialog} from "../http/defaultErrorDialog";
import {HttpClient} from "../http/httpClient";
import {ContactPersonPositionResponseData} from "./contactpersonposition/contactPersonPositionService";
import {ReasonForContactResponseData} from "./reasonsforcontact/reasonForContactService";

@Injectable({
    providedIn: 'root',
  })
export class CustomerManagementService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  saveCustomer(customer: Customer, onSuccess?: (customer: Customer) => void, onError?: (status: number) => void): Customer | null {

    this.httpClient.post('customers/save', customer).then(r =>{
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

  getCustomer(customerId: number, onSuccess: (customer: Customer) => void, onError?: (status: number) => void): void {
    this.httpClient.get('customers/get/' + customerId).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToCustomer(r.data))
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  getAllCustomers(accept: (customers: Customer[]) => void, onError?: (status: number) => void): void{
    this.httpClient.get('customers/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responseCustomers: Customer[] = [];

      for (let dataItem of r.data){
        responseCustomers.push(this.mapToCustomer(dataItem));
      }
      accept(responseCustomers)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  mapToCustomer(data: CustomerResponseData): Customer{

    let id: number = data['customerId'];
    let compName: string = data['companyName'];
    let address: Address | undefined = this.mapAddress(data['address'] as AddressResponseData);
    let logo: Object[] | null = data['logo'];
    let contactPersons: ContactPerson[] = this.mapContactPersons(data['contactPersons'] as ContactPersonResponseData[]);

    return new Customer(compName, address, contactPersons, id);
  }

  mapAddress(data: AddressResponseData | null): Address | undefined{
    if(data == null){
      return undefined;
    }

    return new Address(data.street ?? undefined, data.houseNumber ?? undefined, data.city ?? undefined, CountryUtil.fromValue(data.country), data.zipCode ?? undefined, data.id ?? undefined);
  }

  private mapContactPersons(responseData: ContactPersonResponseData[]): ContactPerson[] {
    let contactPersons: ContactPerson[] = [];

    for (let data of responseData){

      contactPersons.push(
        new ContactPerson(data.firstName, data.lastName, data.email, data.phoneNumber, this.mapReasonForContact(data.reasonForContact), this.mapPosition(data.position))
      );
    }

    return contactPersons;
  }

  private mapReasonForContact(data: ReasonForContactResponseData | undefined): ReasonForContact | undefined{
    if (!data){
      return undefined;
    }

    return new ReasonForContact(data.reason, data.description ?? undefined);
  }

  private mapPosition(data: ContactPersonPositionResponseData | undefined): ContactPersonPosition | undefined{
    if (!data){
      return undefined;
    }

    return new ContactPersonPosition(data.position, data.description ?? undefined);
  }
}

export interface CustomerResponseData {
  customerId: number;
  companyName: string;
  address: Object | null;
  contactPersons: Object[];
  logo: Object[] | null;
}

export interface AddressResponseData {
  id: number | null;
  street: string | null;
  houseNumber: string | null;
  city: string | null;
  country: string | null;
  zipCode: string | null;
}

export interface ContactPersonResponseData {
  id?: number;
  position?: ContactPersonPositionResponseData;
  firstName: string;
  lastName: string;
  email?: string;
  phoneNumber?: string;
  reasonForContact?: ReasonForContactResponseData;
}
