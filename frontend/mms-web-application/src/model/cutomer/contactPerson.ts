import {ContactPersonPosition} from "./contactPersonPosition";
import {EMail} from "./eMail";
import {PhoneNumber} from "./phoneNumber";
import {ReasonForContact} from "./reasonForContact";

export class ContactPerson{

  id: number;
  position: ContactPersonPosition;
  firstName: string;
  lastName: string;
  emails: EMail[];
  phoneNumbers: PhoneNumber[];
  reasonsForContact: ReasonForContact[]

  constructor(id: number, position: ContactPersonPosition, firstName: string, lastName: string, emails?: EMail[], phoneNumbers?: PhoneNumber[], reasonsForContact?: ReasonForContact[]) {
    this.id = id;
    this.position = position;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emails = emails ?? [];
    this.phoneNumbers = phoneNumbers ?? [];
    this.reasonsForContact = reasonsForContact ?? [];
  }
}
