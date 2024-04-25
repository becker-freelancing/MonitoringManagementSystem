import {AsyncPipe} from "@angular/common";
import { Component } from '@angular/core';
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField} from "@angular/material/form-field";
import {MatSlideToggle} from "@angular/material/slide-toggle";
import {map, Observable, startWith} from "rxjs";

export class State {
  constructor(public name: string, public population: string, public flag: string) { }
}

@Component({
  selector: 'app-autocomplete',
  standalone: true,
  imports: [
    MatFormField,
    MatAutocomplete,
    MatOption,
    MatSlideToggle,
    MatFormField,
    MatAutocompleteTrigger,
    ReactiveFormsModule,
    AsyncPipe
  ],
  templateUrl: './autocomplete.component.html',
  styleUrl: './autocomplete.component.css'
})
export class AutocompleteComponent {

  stateCtrl: FormControl;
  filteredStates: Observable<any[]>;

  states: State[] = [
    {
      name: 'Arkansas',
      population: '2.978M',
      // https://commons.wikimedia.org/wiki/File:Flag_of_Arkansas.svg
      flag: 'https://upload.wikimedia.org/wikipedia/commons/9/9d/Flag_of_Arkansas.svg'
    },
    {
      name: 'California',
      population: '39.14M',
      // https://commons.wikimedia.org/wiki/File:Flag_of_California.svg
      flag: 'https://upload.wikimedia.org/wikipedia/commons/0/01/Flag_of_California.svg'
    },
    {
      name: 'Florida',
      population: '20.27M',
      // https://commons.wikimedia.org/wiki/File:Flag_of_Florida.svg
      flag: 'https://upload.wikimedia.org/wikipedia/commons/f/f7/Flag_of_Florida.svg'
    },
    {
      name: 'Texas',
      population: '27.47M',
      // https://commons.wikimedia.org/wiki/File:Flag_of_Texas.svg
      flag: 'https://upload.wikimedia.org/wikipedia/commons/f/f7/Flag_of_Texas.svg'
    }
  ];

  constructor() {
    this.stateCtrl = new FormControl();
    this.filteredStates = this.stateCtrl.valueChanges
      .pipe(
        startWith(''),
        map(state => state ? this.filterStates(state) : this.states.slice())
      );
  }

  filterStates(name: string) {
    return this.states.filter(state =>
      state.name.toLowerCase().indexOf(name.toLowerCase()) === 0);
  }

  onEnter(evt: any){
    if (evt.source.selected) {
      alert("hello ");
    }
  }
}
