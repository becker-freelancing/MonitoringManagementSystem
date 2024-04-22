import {JsonPipe} from "@angular/common";
import {AxiosError} from "axios";

export class DefaultErrorDialog {

  constructor(public reason: AxiosError) {

  }

  show() {
    alert("Oops! Es ist ein Fehler Aufgetreten:\n\n" +
      "ErrorCode: " + this.reason.code + "\n" +
      "Errors: " + JSON.stringify(this.reason.cause));
  }
}
