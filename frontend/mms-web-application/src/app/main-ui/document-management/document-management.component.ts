import {Component, ViewChild} from '@angular/core';
import {FileExplorerComponent} from "./file-explorer/file-explorer.component";
import {FileExplorerPathHintComponent} from "./file-explorer-path-hint/file-explorer-path-hint.component";
import {FileExplorerMenuBarComponent} from "./file-explorer-menu-bar/file-explorer-menu-bar.component";
import {FilePathWithDocument} from "../../../model/files/filePathWithDocument";

@Component({
  selector: 'app-document-management',
  standalone: true,
  imports: [
    FileExplorerComponent,
    FileExplorerPathHintComponent,
    FileExplorerMenuBarComponent
  ],
  templateUrl: './document-management.component.html',
  styleUrl: './document-management.component.css'
})
export class DocumentManagementComponent {

  filePath = "/";
  selectedDocument?: FilePathWithDocument = undefined;

  @ViewChild("fileExplorer") fileExplorer!: FileExplorerComponent;

  onFilePathChange(filePath: string) {
    this.filePath = filePath;
  }

  onDocumentSelectChange(selectedDocument: FilePathWithDocument) {
    this.selectedDocument = selectedDocument;
  }

  onUpdateNeeded() {
    this.fileExplorer.fetchElementsForDir(this.filePath);
  }
}
