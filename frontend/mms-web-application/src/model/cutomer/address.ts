import {Country} from "./country";

export class Address {

  street: string | null;
  houseNumber: string | null;
  city: string | null;
  country: Country | null;
  zipCode: string | null;
  addressId?: number;

  constructor(street: string | null, houseNumber: string | null, city: string | null, country: Country | null, zipCode: string | null, addressId?: number) {

    this.street = street;
    this.houseNumber = houseNumber;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
    this.addressId = addressId;
  }
}
