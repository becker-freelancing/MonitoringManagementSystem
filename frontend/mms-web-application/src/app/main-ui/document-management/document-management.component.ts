import { Component } from '@angular/core';
import {FileExplorerComponent} from "./file-explorer/file-explorer.component";
import {FileExplorerPathHintComponent} from "./file-explorer-path-hint/file-explorer-path-hint.component";

@Component({
  selector: 'app-document-management',
  standalone: true,
  imports: [
    FileExplorerComponent,
    FileExplorerPathHintComponent
  ],
  templateUrl: './document-management.component.html',
  styleUrl: './document-management.component.css'
})
export class DocumentManagementComponent {

  filePath = "/";

  onFilePathChange(filePath: string) {
    this.filePath = filePath;
  }
}
