import {Country} from "./country";

export class Address {

  street: string | undefined;
  houseNumber: string | undefined;
  city: string | undefined;
  country: Country | undefined;
  zipCode: string | undefined;
  addressId?: number;

  constructor(street?: string, houseNumber?: string, city?: string, country?: Country, zipCode?: string, addressId?: number) {

    this.street = street;
    this.houseNumber = houseNumber;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
    this.addressId = addressId;
  }
}
