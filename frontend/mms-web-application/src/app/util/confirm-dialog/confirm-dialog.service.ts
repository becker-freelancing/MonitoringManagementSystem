import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "./confirm-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class ConfirmDialogService {

  title: string = '';
  content: string = '';

  constructor(private dialog: MatDialog) {
  }

  showCustomDialog(data: ConfirmDialogData, onConfirm: () => void, onCancel: () => void): void {

    this.dialog.open(ConfirmDialogComponent,{
      data: data
    }).afterClosed()
      .subscribe(value => {
        if (value === true){
          onConfirm();
        } else if(value === false){
          onCancel();
        }
      });
  }

  showConfirmDeleteDialog(onConfirm: () => void, onCancel: () => void): void {
    let title = "Löschen";
    let content = "Soll der ausgewählte Eintrag wirklich gelöscht werden?\n\nDiese Aktion kann nicht rückgängig gemacht werden!";
    let confirmDialogData = new ConfirmDialogData(title, content);

    this.dialog.open(ConfirmDialogComponent,{
      data: confirmDialogData
    }).afterClosed()
      .subscribe(value => {
        if (value === true){
          onConfirm();
        } else if(value === false){
          onCancel();
        }
      });
  }

  showConfirmCancelDialog(onConfirm: () => void, onCancel: () => void): void {
    let title = "Abbrechen";
    let content = "Soll die aktuelle Aktion wirklich abgebrochen werden?\n\nAlle Änderungen gehen verloren!";
    let confirmDialogData = new ConfirmDialogData(title, content);

    this.dialog.open(ConfirmDialogComponent,{
      data: confirmDialogData
    }).afterClosed()
      .subscribe(value => {
        if (value === true){
          onConfirm();
        } else if(value === false){
          onCancel();
        }
      });
  }
}

export class ConfirmDialogData {
  title: string;
  content: string;


  constructor(title: string, content: string) {
    this.title = title;
    this.content = content;
  }
}
