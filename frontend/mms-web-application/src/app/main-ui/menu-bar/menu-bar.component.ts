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
}
