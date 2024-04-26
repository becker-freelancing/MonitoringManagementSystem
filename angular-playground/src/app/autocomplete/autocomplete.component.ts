import {NgForOf} from "@angular/common";
import { Component } from '@angular/core';
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-autocomplete',
  standalone: true,
  imports: [
    MatFormField,
    ReactiveFormsModule,
    MatAutocompleteTrigger,
    MatAutocomplete,
    MatOption,
    NgForOf,
    MatInput

  ],
  templateUrl: './autocomplete.component.html',
  styleUrl: './autocomplete.component.css'
})
export class AutocompleteComponent {
  myControl = new FormControl();
  states: { value: string; display: string; }[] = [];

  constructor(){
    this.loadStates();
  }
  //build list of states as map of key-value pairs
  loadStates() {
    var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
         Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
         Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
         Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
         North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
         South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
         Wisconsin, Wyoming';
    this.states =  allStates.split(/, +/g).map( function (state) {
      return {
        value: state.toUpperCase(),
        display: state
      };
    });
  }
}
