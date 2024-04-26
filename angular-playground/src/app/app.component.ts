import {AsyncPipe, NgForOf} from "@angular/common";
import {Component, forwardRef, OnInit} from '@angular/core';
import {FormControl, NG_VALUE_ACCESSOR, ReactiveFormsModule} from "@angular/forms";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import { RouterOutlet } from '@angular/router';
import {map, Observable, startWith} from "rxjs";
import {AutocompleteComponent} from "./autocomplete/autocomplete.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AutocompleteComponent, MatFormField, MatInput, MatAutocomplete, MatOption, NgForOf, MatAutocompleteTrigger, ReactiveFormsModule, AsyncPipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend-playground';

  options: string[] = ['Angular', 'React', 'Vue'];

  objectOptions = [
    {name: 'Angular'},
    {name: 'Angular Material'},
    {name: 'React'},
    {name: 'Vue'}
  ];

  myControl = new FormControl();

  filteredOptions: Observable<string[]> = this.myControl.valueChanges;

  ngOnInit(): void {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this.filter(value))
    )
  }


  //Wird benutzt, um im Input Feld nicht [object Object] angezeigt zu bekommen, sondern einen schönen Namen
  displayFun(subject: { name: any; }){
    return subject ? subject.name : undefined;
  }

  private filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
}
