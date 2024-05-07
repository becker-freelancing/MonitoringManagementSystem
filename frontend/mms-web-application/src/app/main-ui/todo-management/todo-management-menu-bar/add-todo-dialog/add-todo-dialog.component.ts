import {NgForOf, NgIf} from "@angular/common";
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose, MatDialogContainer,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {Todo} from "../../../../../model/todo/todo";

@Component({
  selector: 'app-add-project-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, NgForOf, FormsModule, MatDialogContainer, MatFormField, MatInput, ReactiveFormsModule, NgIf, MatRadioGroup, MatRadioButton],
  templateUrl: './add-todo-dialog.component.html',
  styleUrl: './add-todo-dialog.component.css',
  providers: []
})
export class AddTodoDialogComponent implements OnInit{

  @Output('addedTodo') savedTodoOutput = new EventEmitter<Todo>();

  // form: FormGroup;

  // projectName: string = '';
  //
  // projectNameNotValid: boolean = false;


  constructor(
    fb: FormBuilder,
    private dialogRef: MatDialogRef<AddTodoDialogComponent>) {
    // this.form = fb.group({
    //   projectName: [this.projectName, Validators.required]
    // });
  }

  ngOnInit() {

  }

  saveTodo() {
    // if(this.form.valid) {
    //   let formValues = this.form.value;
    //
    //   let project = new Project(formValues['projectName'], new Date());
    //   this.dialogRef.close(project);
    // } else {
    //   this.projectNameNotValid = true;
    // }
  }
}
