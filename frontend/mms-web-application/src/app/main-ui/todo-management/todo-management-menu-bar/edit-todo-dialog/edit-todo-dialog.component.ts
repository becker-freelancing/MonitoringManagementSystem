import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatNativeDateModule,
  NativeDateAdapter
} from "@angular/material/core";
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatHint, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatTab, MatTabContent, MatTabGroup} from "@angular/material/tabs";
import {MatTooltip} from "@angular/material/tooltip";
import {Customer} from "../../../../../model/cutomer/customer";
import {Todo} from "../../../../../model/todo/todo";
import {TodoCategory} from "../../../../../model/todo/todoCategory";
import {TodoManagementTodo} from "../../../../../model/todo/todoManagementTodo";
import {DateTime} from "../../../../../model/util/DateTime";
import {CustomerManagementService} from "../../../../../services/customermanagement/customerManagementService";
import {TodoCategoryService} from "../../../../../services/todo/todoCategoryService";

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
  selector: 'app-edit-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatTabGroup, MatTab, MatFormField, MatInput, ReactiveFormsModule, MatTabContent, MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable, NgOptimizedImage, MatHeaderCellDef, NgIf, NgClass, NgForOf, MatSelect, MatOption, FormsModule, MatTooltip, MatDatepickerToggle, MatDatepickerInput, MatDatepicker, MatHint, MatLabel, MatIcon, MatSuffix, MatNativeDateModule, MatDatepickerModule],
  templateUrl: './edit-todo-dialog.component.html',
  styleUrl: './edit-todo-dialog.component.css',
  providers: [
    { provide: DateAdapter, useClass: NativeDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_CUSTOM_DATE_FORMATS }
  ]
})
export class EditTodoDialogComponent implements OnInit {

  form: FormGroup;

  uiId: number;
  todoId?: number;
  todoTitle: string = '';
  shortDescription?: string;
  longDescription?: string;
  endTime?: Date;
  customer?: Customer;
  category?: TodoCategory;

  todoTitleNotValid: boolean = false;
  customers: Customer[] = [];
  categories: TodoCategory[] = [];


  constructor(
    fb: FormBuilder,
    private dialogRef: MatDialogRef<EditTodoDialogComponent>,
    private customerService: CustomerManagementService,
    private categoryService: TodoCategoryService,
    @Inject(MAT_DIALOG_DATA) public data: TodoManagementTodo) {

    this.uiId = data.uiId;
    this.todoId = data.todo.todoId;
    this.todoTitle = data.todo.title;
    this.shortDescription = data.todo.shortDescription;
    this.longDescription = data.todo.longDescription;
    this.endTime = data.todo.endTime;
    this.customer = data.customer;
    this.category = data.todo.category;

    this.form = fb.group({
      todoTitle: [this.todoTitle, Validators.required],
      shortDescription: [this.shortDescription],
      longDescription: [this.longDescription],
      endTime: [this.endTime],
      customer: [this.customer],
      category: [this.category]
    });

    customerService.getAllCustomers((customers) => this.customers = customers);
    categoryService.getAllCategories((categories) => this.categories = categories);
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
        new DateTime(),
        formValues.shortDescription,
        formValues.longDescription,
        formValues.endTime,
        undefined,
        formValues.customer?.customerId,
        formValues.category,
        this.todoId
      );
      let todoManagementTodo = new TodoManagementTodo(this.uiId, todo);
      this.dialogRef.close(todoManagementTodo);
    }
  }

  close() {
    this.dialogRef.close(undefined)
  }
}
