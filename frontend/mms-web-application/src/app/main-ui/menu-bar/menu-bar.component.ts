import {NgClass, NgOptimizedImage} from "@angular/common";
import {Component} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs";

@Component({
  selector: 'menu-bar',
  standalone: true,
  imports: [
    NgOptimizedImage,
    NgClass
  ],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.css'
})
export class MenuBarComponent {

  currentlySelected: NavigationItem | null = NavigationItem.DASHBOARD;

  constructor(private router: Router) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event) => {
        let currentRoute = (event as NavigationEnd).url;
          if(currentRoute == '/dashboard') {
            this.currentlySelected = NavigationItem.DASHBOARD;
          } else if(currentRoute == '/working-hours-management'){
            this.currentlySelected = NavigationItem.WORKING_HOURS;
          } else if(currentRoute == '/customer-management') {
            this.currentlySelected = NavigationItem.CUSTOMER_MANAGEMENT;
          } else if(currentRoute == '/todo-management'){
            this.currentlySelected = NavigationItem.TODO;
          } else if(currentRoute == '/project-management'){
            this.currentlySelected = NavigationItem.PROJECT_MANAGEMENT;
          } else if(currentRoute == '/income-management'){
            this.currentlySelected = NavigationItem.INCOME_MANAGEMENT;
          } else if(currentRoute == '/documents'){
            this.currentlySelected = NavigationItem.DOCUMENTS;
          } else if(currentRoute == '/settings'){
            this.currentlySelected = NavigationItem.SETTINGS;
          }
      });
  }

  getStyleClass(item: NavigationItem): string {
    if (item === this.currentlySelected){
      return 'selected-menu-bar-item';
    }

    return '';
  }

  protected readonly NavigationItem = NavigationItem;
}

export enum NavigationItem {
  DASHBOARD,
  TODO,
  WORKING_HOURS,
  CUSTOMER_MANAGEMENT,
  PROJECT_MANAGEMENT,
  INCOME_MANAGEMENT,
  DOCUMENTS,
  SETTINGS
}

