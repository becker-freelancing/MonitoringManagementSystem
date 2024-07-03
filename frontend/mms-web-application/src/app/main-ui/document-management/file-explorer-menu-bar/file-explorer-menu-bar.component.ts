import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {NgClass, NgOptimizedImage} from "@angular/common";
import {FilePathWithDocument} from "../../../../model/files/filePathWithDocument";

@Component({
  selector: 'app-file-explorer-menu-bar',
  standalone: true,
  imports: [
    NgOptimizedImage,
    NgClass
  ],
  templateUrl: './file-explorer-menu-bar.component.html',
  styleUrl: './file-explorer-menu-bar.component.css'
})
export class FileExplorerMenuBarComponent implements OnChanges{

  @Input("document") document?: FilePathWithDocument;

  enableDeleteDirectoryButton: boolean = false;
  enableDeleteDocumentButton: boolean = false;

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["document"]){
      this.documentChanged();
    }
  }

  private documentChanged() {
    if(this.document){
      this.enableDeleteDirectoryButton = this.document.isDirectory();
      this.enableDeleteDocumentButton = !this.document.isDirectory();
    } else {
      this.enableDeleteDocumentButton = false;
      this.enableDeleteDirectoryButton = false;
    }
  }
}
