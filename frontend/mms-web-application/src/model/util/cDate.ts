export class CDate {

  static now(): CDate {
    return CDate.fromDate(new Date());
  }

  static fromDate(date: Date): CDate {
    return new CDate(
      date.getFullYear(),
      date.getMonth() + 1,
      date.getDate()
    );
  }

  static fromDateString(date?: string): CDate {
    if(!date){
      return CDate.now();
    }
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

  public getTime(): number {
    const date = this.asDate();
    return date.getTime();
  }

  private fillZero(num: number): string {
    if(num < 10){
      return '0' + num;
    }

    return num.toString();
  }

  isEqual(other: CDate): boolean {
    return other.getTime() == this.getTime();
  }

  isSameWeek(otherDate: CDate): boolean {
    let lastMonday = this.getLastMonday();
    let nextSunday = this.getNextSunday();

    return (lastMonday.isBefore(otherDate) && otherDate.isBefore(nextSunday)) || (otherDate.isEqual(lastMonday) || otherDate.isEqual(nextSunday));
  }

  getLastMonday(): CDate {
    let subtract = this.getDayOfWeek() - 1;
    if(this.day - subtract > 0){
      return new CDate(this.year, this.month, this.day - subtract);
    } else {
      if(this.month == 1){
        let daysOfMonth = CDate.daysOfMonth(12, this.year - 1);
        daysOfMonth = daysOfMonth + (this.day - subtract);
        return new CDate(this.year - 1, 12, daysOfMonth);
      } else {
        let daysOfMonth = CDate.daysOfMonth(this.month - 1, this.year);
        daysOfMonth = daysOfMonth + (this.day - subtract);
        return new CDate(this.year, this.month - 1, daysOfMonth);
      }
    }
  }

  getNextSunday(): CDate {
    let add = 7 - this.getDayOfWeek();
    if(this.day + add <= CDate.daysOfMonth(this.month, this.year)){
      return new CDate(this.year, this.month, this.day + add);
    } else {
      if(this.month == 12){
        let daysOfMonth =  add - (CDate.daysOfMonth(12, this.year) - this.day);
        return new CDate(this.year + 1, 1, daysOfMonth);
      } else {
        let daysOfMonth = add - (CDate.daysOfMonth(this.month, this.year) - this.day);
        return new CDate(this.year, this.month + 1, daysOfMonth);
      }
    }
  }

  getLastMonthStart(){
    return new CDate(this.year, this.month, 1);
  }

  getNextMonthStart(){
    let month = this.month + 1;

    if(month > 12){
      return new CDate(this.year + 1, 1, 1);
    }

    return new CDate(this.year, month, 1);
  }

  getLastYearStart(){
    return new CDate(this.year, 1, 1);
  }

  getNextYearStart(){
    return new CDate(this.year + 1, 1, 1);
  }

  getLastQuarterStart() {
    let div3 = Math.floor(this.month / 3);

    return new CDate(this.year, div3 * 3, 1);
  }

  getNextQuarterStart(){
    let div3 = Math.floor(this.month / 3) + 1;

    if(div3 == 4){
      return new CDate(this.year + 1, 1, 1);
    }

    return new CDate(this.year, div3 * 3, 1);
  }

  toString(): string {
    const yearStr = this._year.toString().padStart(4, '0');
    const monthStr = (this._month < 10 ? '0' : '') + this._month;
    const dayStr = (this._day < 10 ? '0' : '') + this._day;
    return `${dayStr}.${monthStr}.${yearStr}`;
  }

  toStringWithoutYear(): string {
    const monthStr = (this._month < 10 ? '0' : '') + this._month;
    const dayStr = (this._day < 10 ? '0' : '') + this._day;
    return `${dayStr}.${monthStr}.`;
  }

  getDayOfWeek(): number {
    let firstCalc = this.day + this.getMonthValueForDayOfWeekCalculation() + this.getYearValueForDayOfWeekCalculation();
    let mod =  firstCalc % 7;
    if(mod == 0){
      return 7;
    }
    return mod;
  }

  private getYearValueForDayOfWeekCalculation(): number {
    let year = Math.floor(Number(String(this.year).substring(2)) / 4) + Number(String(this.year).substring(2));
    return year % 7;
  }

  private getMonthValueForDayOfWeekCalculation(): number {
    if(this.isLeapYear()){
      if(this.month == 1){
        return 5;
      } else if(this.month == 2){
        return 1;
      }
    }
    let monthValues = [6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4];

    return monthValues[this.month - 1];
  }

  getDayOfWeekAsString(): string{
    let days = ["Fr", "Sa", "So", "Mo", "Di", "Mi", "Do"]
    return days[this.asDate().getDay()]
  }

  isLeapYear(): boolean {
   return CDate.isLeapYear(this.year);
  }

  isBefore(date: CDate): boolean {
    if(this.year < date.year){
      return true;
    } else if(this.month < date.month){
      return true;
    } else if(this.month == date.month && this.day < date.day){
      return true;
    }
    return false;
  }

  isAfter(date: CDate): boolean {
    return !this.isBefore(date) && !this.isBefore(date);
  }

  private asDate(): Date {
    return new Date(this._year, this._month, this._day);
  }

  nextDay(): CDate {
    let newDay = this.day + 1;

    if(newDay > CDate.daysOfMonth(this.month, this.year)){
      let newMonth = this.month + 1;
      if(newMonth > 12){
        return new CDate(this.year + 1, 1, 1);
      }

      return new CDate(this.year, this.month + 1, 1);
    }

    return new CDate(this.year, this.month, newDay);
  }

  lastDay(): CDate {
    let newDay = this.day - 1;

    if(newDay === 0){
      let newMonth = this.month - 1;
      if(newMonth === 0){
        return new CDate(this.year - 1, 12, 31);
      }

      return new CDate(this.year, newMonth, CDate.daysOfMonth(newMonth, this.year))
    }

    return new CDate(this.year, this.month, newDay);
  }

  static daysOfMonth(month: number, year: number): number {
    if(month == 2 && CDate.isLeapYear(year)){
      return 29;
    }
    let days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days[month - 1];
  }

  static isLeapYear(year: number): boolean {
    let divBy4 = year % 4 === 0;
    let divBy100 = year % 100 === 0;
    if(divBy4 && !divBy100){
      return true;
    }
    return year % 400 === 0;
  }

  isSameQuarter(date: CDate) {
    let lastQuarterStart = this.getLastQuarterStart();
    let nextQuarterStart = this.getNextQuarterStart();

    return date.isEqual(lastQuarterStart) || (date.isAfter(lastQuarterStart) && date.isBefore(nextQuarterStart));
  }

  isSameMonth(date: CDate) {
    let lastMonthStart = this.getLastMonthStart();
    let nextMonthStart = this.getNextMonthStart();

    return date.isEqual(lastMonthStart) || (date.isAfter(lastMonthStart) && date.isBefore(nextMonthStart));
  }
}
