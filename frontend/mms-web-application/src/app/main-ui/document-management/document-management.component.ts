import { Component } from '@angular/core';
import {FileExplorerComponent} from "./file-explorer/file-explorer.component";

@Component({
  selector: 'app-document-management',
  standalone: true,
  imports: [
    FileExplorerComponent
  ],
  templateUrl: './document-management.component.html',
  styleUrl: './document-management.component.css'
})
export class DocumentManagementComponent {

}
