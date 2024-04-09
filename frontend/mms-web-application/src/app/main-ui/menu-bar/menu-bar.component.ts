import {NgOptimizedImage} from "@angular/common";
import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'menu-bar',
  standalone: true,
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.css'
})
export class MenuBarComponent {

  constructor(private router: Router) {
  }

  rootToSettings() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/settings'){
      return;
    }

    this.router.navigate(['settings']).then(success => {
      if (!success) {
        alert('Einstellungen nicht gefunden');
      }
    });
  }

  rootToIncome() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/income-management'){
      return;
    }

    this.router.navigate(['income-management']).then(success => {
      if (!success) {
        alert('Einkommens-Management nicht gefunden');
      }
    });
  }

  rootToProjects() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/project-management'){
      return;
    }

    this.router.navigate(['project-management']).then(success => {
      if (!success) {
        alert('Projekt-Management nicht gefunden');
      }
    });
  }

  rootToCustomers() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/customer-management'){
      return;
    }

    this.router.navigate(['customer-management']).then(success => {
      if (!success) {
        alert('Kunden-Management nicht gefunden');
      }
    });
  }

  rootToWorkingHours() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/working-hours-management'){
      return;
    }

    this.router.navigate(['working-hours-management']).then(success => {
      if (!success) {
        alert('Arbeitszeit-Management nicht gefunden');
      }
    });
  }

  rootToToDoList() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/todo-management'){
      return;
    }

    this.router.navigate(['todo-management']).then(success => {
      if (!success) {
        alert('ToDo-Management nicht gefunden');
      }
    });
  }

  rootToDashboard() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/dashboard'){
      return;
    }

    this.router.navigate(['dashboard']).then(success => {
      if (!success) {
        alert('Dashboard nicht gefunden');
      }
    });
  }

  rootToDocuments() {
    let currentNavigation = this.router.url;
    if(currentNavigation === '/documents'){
      return;
    }

    this.router.navigate(['documents']).then(success => {
      if (!success) {
        alert('Dokumente nicht gefunden');
      }
    });
  }
}
