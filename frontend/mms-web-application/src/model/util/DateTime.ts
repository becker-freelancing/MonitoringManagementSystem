export class DateTime extends Date{

  public static fromDate(date: Date | null | undefined): DateTime | undefined {
    if(date === undefined || date === null){
      return undefined;
    }

    return new DateTime(date);
  }

  override toJSON(key?: any): string {
    return this.getFullYear() + '-' +
      this.fillZero(this.getMonth() + 1) + '-' +
      this.fillZero(this.getDate()) + 'T' +
      this.fillZero(this.getHours()) + ':' +
      this.fillZero(this.getMinutes()) + ':' +
      this.fillZero(this.getSeconds()) + '.' +
      this.getMilliseconds() + 'Z';
  }

  private fillZero(num: number): string {
    if(num < 10){
      return '0' + num;
    }

    return num.toString();
  }

}
