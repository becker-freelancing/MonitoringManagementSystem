import {NgForOf, NgIf} from "@angular/common";
import {Component, Inject, OnInit} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef
} from "@angular/material/dialog";
import {Customer} from "../../../../../model/cutomer/customer";
import {Project} from "../../../../../model/project/project";
import {CDate} from "../../../../../model/util/cDate";
import {Time} from "../../../../../model/util/time";
import {WorkingHourManagementWorkingHour} from "../../../../../model/workinghours/workingHourManagementWorkingHour";

@Component({
  selector: 'app-working-hour-exists-warning-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    NgIf,
    NgForOf,
    MatDialogActions,
    MatButton,
    MatDialogClose
  ],
  templateUrl: './working-hour-exists-warning-dialog.component.html',
  styleUrl: './working-hour-exists-warning-dialog.component.css'
})
export class WorkingHourExistsWarningDialogComponent implements OnInit {


  constructor(
    private dialogRef: MatDialogRef<WorkingHourExistsWarningDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: WorkingHourExistsWarningDialogData
  ) {
  }

  ngOnInit(): void {
  }


  closeDialog(recordWorkingHour: boolean) {
    this.dialogRef.close(recordWorkingHour);
  }
}


export interface WorkingHourExistsWarningDialogData {

  newWorkingHour: WorkingHourExistsWorkingHour;
  overlappedWorkingHours: WorkingHourExistsWorkingHour[];
}

export interface WorkingHourExistsWorkingHour {

  customer?: Customer;
  date?: CDate;
  startTime?: Time;
  endTime?: Time;
  project?: Project;
}
