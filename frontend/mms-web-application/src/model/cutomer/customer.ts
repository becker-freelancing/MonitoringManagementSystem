import {Address} from "./address";
import {ContactPerson} from "./contactPerson";

export class Customer{

  customerId: number;
  companyName: string;
  address?: Address;
  contactPersons: ContactPerson[];

  constructor(compName: string, addr?: Address, contactPers?: ContactPerson[], custId?: number) {
    this.customerId = custId ?? -1;
    this.companyName = compName;
    this.address = addr;
    this.contactPersons = contactPers ?? [];
  }
}
