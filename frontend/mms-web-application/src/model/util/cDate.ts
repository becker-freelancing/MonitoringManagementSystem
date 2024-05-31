export class CDate {

  static fromDate(date: Date): CDate {
    return new CDate(
      date.getFullYear(),
      date.getMonth(),
      date.getDate()
    );
  }

  static fromDateString(date: string): CDate {
    let split = date.split("-");

    if(split.length === 3){
      return new CDate(
        Number(split[0]),
        Number(split[1]),
        Number(split[2])
      )
    }

    return CDate.fromDate(new Date());
  }

  private _year: number;
  private _month: number;
  private _day: number;


  constructor(year: number, month: number, day: number) {
    this._year = year;
    this._month = month;
    this._day = day;
  }


  get year(): number {
    return this._year;
  }

  get month(): number {
    return this._month;
  }

  get day(): number {
    return this._day;
  }

  toJSON(key?: any): string {
    return this.year + '-' +
      this.fillZero(this.month) + '-' +
      this.fillZero(this.day)
  }

  private fillZero(num: number): string {
    if(num < 10){
      return '0' + num;
    }

    return num.toString();
  }
}
