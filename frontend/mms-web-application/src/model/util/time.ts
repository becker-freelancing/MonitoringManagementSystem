export class Time {

  static fromDate(date: Date): Time {
    return new Time(
      date.getHours(),
      date.getMinutes(),
      date.getSeconds()
    );
  }

  static fromTimeString(time: string): Time {

    let split = time.split(':');

    if (split.length === 3) {
      return new Time(
        Number(split[0]),
        Number(split[1]),
        Number(split[2])
      )
    }

    return new Time(
      Number(split[0]),
      Number(split[1]),
      0
    )
  }

  private _hour: number;
  private _minute: number;
  private _second: number;


  constructor(hour: number, minute: number, second: number) {
    this._hour = hour;
    this._minute = minute;
    this._second = second;
  }


  get hour(): number {
    return this._hour;
  }

  get minute(): number {
    return this._minute;
  }

  get second(): number {
    return this._second;
  }

  calcAbsoluteDurationTo(to: Time): TimeDuration {

    const startInSeconds = this.getTime()
    const endInSeconds = to.getTime();

    let differenceInSeconds = endInSeconds - startInSeconds;

    if (differenceInSeconds < 0) {
      differenceInSeconds += 24 * 3600;
    }

    const hours = Math.floor(differenceInSeconds / 3600);
    const minutes = Math.floor((differenceInSeconds % 3600) / 60);
    const seconds = differenceInSeconds % 60;

    return new class implements TimeDuration {
      hours: number = hours;
      minutes: number = minutes;
      seconds: number = seconds;
    }
  }

  isBefore(other?: Time): boolean {
    if (other == undefined) {
      return false;
    }
    if (this._hour < other._hour) {
      return true;
    }
    if (this._hour === other._hour) {
      if (this._minute < other._minute) {
        return true;
      }
      if (this._minute === other._minute) {
        return this._second < other._second;
      }
    }
    return false;
  }

  isAfter(other?: Time): boolean {
    if(!other){
      return false;
    }

    return !this.isBefore(other) && !this.isEqual(other);
  }

  toJSON(key?: any): any {
    return this.fillZero(this._hour) + ':' + this.fillZero(this._minute) + ':' + this.fillZero(this._second);
    // return {
    //   hour: this.hour,
    //   minute: this.minute,
    //   second: this.second,
    //   nano: 0
    // };
  }

  private fillZero(num: number): string {
    if (num < 10) {
      return '0' + num;
    }

    return num.toString();
  }

  getTime(): number {
    return this._hour * 3600 + this._minute * 60 + this._second;
  }

  toString(): string {
    const hourStr = (this.hour < 10 ? '0' : '') + this.hour;
    const minuteStr = (this.minute < 10 ? '0' : '') + this.minute;
    const secondStr = (this.second < 10 ? '0' : '') + this.second;
    return `${hourStr}:${minuteStr}:${secondStr}`;
  }

  toStringWithoutSeconds(): string {
    const hourStr = (this.hour < 10 ? '0' : '') + this.hour;
    const minuteStr = (this.minute < 10 ? '0' : '') + this.minute;
    return `${hourStr}:${minuteStr}`;
  }

  isEqual(other?: Time) {
    if(!other){
      return false;
    }
    return this.hour == other.hour && this.minute == other.minute && this.second == other.second;
  }
}


export interface TimeDuration {
  hours: number;
  minutes: number;
  seconds: number;
}
