import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MainUiComponent} from "./main-ui/main-ui.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainUiComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'mms-web-application';
}
