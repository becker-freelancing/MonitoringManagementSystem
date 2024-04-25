export enum Country{
  GERMANY= 'Deutschland',
  USA = 'USA',
  SPAIN = 'Spanien',
  SWISS = 'Schweiz'
}

export class CountryUtil {

  static values(): string[]{
    let enumObject = Country;

    const values: string[] = [];
    for (const key in enumObject) {
      if (typeof enumObject[key as keyof typeof enumObject] === 'string') {
        values.push(enumObject[key as keyof typeof enumObject]);
      }
    }
    return values;
  }

  static fromValue(value: string | null): Country | undefined {
    if(value === null){
      return undefined;
    }
   let enumObject: any = Country;

    for (const key in enumObject) {
      if (typeof enumObject[key as keyof typeof enumObject] === 'string') {
        if (enumObject[key as keyof typeof enumObject] === value) {
          return enumObject[key as keyof typeof enumObject];
        }
      }
    }
    return undefined;
  }

  static toString(country: Country | undefined): string | undefined{
    if(country === undefined){
      return undefined;
    }

    for (const key in Country) {
      if (Country[key as keyof typeof Country] === country) {
        return Country[key as keyof typeof Country];
      }
    }
    throw new Error('Ungültiges Land');
  }
}
