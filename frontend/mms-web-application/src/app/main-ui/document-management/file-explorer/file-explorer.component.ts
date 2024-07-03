import {Component, EventEmitter, Output} from '@angular/core';
import {FilePathService} from "../../../../services/files/filePathService";
import {FilePathWithDocument} from "../../../../model/files/filePathWithDocument";
import {FilePath} from "../../../../model/files/filePath";
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-file-explorer',
  standalone: true,
  imports: [
    NgIf,
    NgOptimizedImage,
    NgForOf,
    NgClass
  ],
  templateUrl: './file-explorer.component.html',
  styleUrl: './file-explorer.component.css'
})
export class FileExplorerComponent {

  @Output("filePathChange") filePathChangeOutput = new EventEmitter<string>();
  @Output("documentSelectChange") documentSelectChange = new EventEmitter<FilePathWithDocument>();


  filePathService: FilePathService;
  currentDir: string = "";
  showGoToUpperDir =  false;
  selectedUiId: number = -1;

  childDirs: FilePathWithDocumentAndId[] = []
  childDocuments: FilePathWithDocumentAndId[] = []

  constructor(filePathService: FilePathService) {
    this.filePathService = filePathService;
    this.fetchElementsForDir("root")
  }

  onGoToUpperDir(){
    let split = this.currentDir.split("\\");
    split.pop();
    let upperDir = split.join("\\");
    this.fetchElementsForDir(upperDir);
  }

  onGoToDeeperDir(dir: string){
    this.fetchElementsForDir(dir);
  }

  fetchElementsForDir(dir: string){
    this.currentDir = dir;
    this.showGoToUpperDir = dir !== "root";
    this.onSelectDir(-1);
    this.filePathService.getChildrenFromPath(new FilePath(dir), (children) => {
      this.setChildDirs(children);
      this.setChildDocuments(children);
      this.filePathChangeOutput.emit(dir);
    })
  }

  private setChildDirs(children: FilePathWithDocument[]){
    this.childDirs = [];

    let dirs =  children.filter(doc => doc.isDirectory());
    let id = 0;

    for (const dir of dirs) {
      this.childDirs.push({uiId: id, document: dir});
      id++;
    }
  }

  private setChildDocuments(children: FilePathWithDocument[]){
    this.childDocuments = [];

    let dirs =  children.filter(doc => !doc.isDirectory());
    let id = this.childDirs.length;

    for (const dir of dirs) {
      this.childDocuments.push({uiId: id, document: dir});
      id++;
    }
  }

  onSelectDir(uiId: number) {
    this.selectedUiId = uiId;

    let found = false;

    for (const childDir of this.childDirs) {
      if(childDir.uiId === uiId){
        found = true;
        this.documentSelectChange.emit(childDir.document);
      }
    }
    for (const childDir of this.childDocuments) {
      if(childDir.uiId === uiId){
        found = true;
        this.documentSelectChange.emit(childDir.document);
      }
    }

    if(!found){
      this.documentSelectChange.emit(undefined);
    }
  }

  getCSSClass(uiId: number) {
    if(uiId === this.selectedUiId){
      return "horizontal-div file-explorer-file selected-file-explorer-file"
    }

    return "horizontal-div file-explorer-file"
  }
}


interface FilePathWithDocumentAndId {
  uiId: number;
  document: FilePathWithDocument;
}
