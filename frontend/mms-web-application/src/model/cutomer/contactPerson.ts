import {ContactPersonPosition} from "./contactPersonPosition";
import {EMail} from "./eMail";
import {PhoneNumber} from "./phoneNumber";
import {ReasonForContact} from "./reasonForContact";

export class ContactPerson{

  id?: number;
  position?: ContactPersonPosition;
  firstName: string;
  lastName: string;
  email: EMail | null;
  phoneNumber: PhoneNumber | null;
  reasonForContact: ReasonForContact | null;

  constructor(firstName: string, lastName: string, email?: EMail, phoneNumber?: PhoneNumber, reasonForContact?: ReasonForContact, position?: ContactPersonPosition, id?: number) {
    this.id = id;
    this.position = position;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email ?? null;
    this.phoneNumber = phoneNumber ?? null;
    this.reasonForContact = reasonForContact ?? null;
  }
}
