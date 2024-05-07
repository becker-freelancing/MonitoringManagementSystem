import {NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, NativeDateAdapter} from "@angular/material/core";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {
  MatDialogActions,
  MatDialogClose, MatDialogContainer,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {Customer} from "../../../../../model/cutomer/customer";
import {Todo} from "../../../../../model/todo/todo";
import {TodoCategory} from "../../../../../model/todo/todoCategory";

export const MY_CUSTOM_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-add-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, NgForOf, FormsModule, MatDialogContainer, MatFormField, MatInput, ReactiveFormsModule, NgIf, MatRadioGroup, MatRadioButton, MatDatepicker, MatDatepickerInput, MatDatepickerToggle, MatLabel, MatSuffix],
  templateUrl: './add-todo-dialog.component.html',
  styleUrl: './add-todo-dialog.component.css',
  providers: [
    { provide: DateAdapter, useClass: NativeDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_CUSTOM_DATE_FORMATS }
  ]
})
export class AddTodoDialogComponent implements OnInit{

  @Output('addedTodo') savedTodoOutput = new EventEmitter<Todo>();

  form: FormGroup;

  todoTitle: string = '';
  shortDescription?: string;
  longDescription?: string;
  endTime?: Date;
  customer?: Customer;
  category?: TodoCategory;

  todoTitleNotValid: boolean = false;


  constructor(
    fb: FormBuilder,
    private dialogRef: MatDialogRef<AddTodoDialogComponent>) {
    this.form = fb.group({
      todoTitle: [this.todoTitle, Validators.required],
      shortDescription: [this.shortDescription],
      longDescription: [this.longDescription],
      endTime: [this.endTime],
      customer: [this.customer],
      category: [this.category]
    });
  }

  ngOnInit() {

  }

  saveTodo() {
    if (!this.form.valid) {
      this.todoTitleNotValid = true;
    } else {
      let formValues = this.form.value;

      let todo = new Todo(
        formValues.todoTitle,
        new Date(),
        formValues.shortDescription,
        formValues.longDescription,
        formValues.endTime,
        undefined,
        formValues.customer?.customerId,
        formValues.category
      );
      this.dialogRef.close(todo);
    }
  }
}
