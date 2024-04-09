import {NgOptimizedImage} from "@angular/common";
import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {MenuBarComponent} from "./menu-bar/menu-bar.component";

@Component({
  selector: 'main-ui',
  standalone: true,
  imports: [
    NgOptimizedImage,
    MenuBarComponent,
    DashboardComponent,
    RouterOutlet
  ],
  templateUrl: './main-ui.component.html',
  styleUrl: './main-ui.component.css'
})
export class MainUiComponent {

}
