import { Component } from '@angular/core';
import {FilePathService} from "../../../../services/files/filePathService";
import {FilePathWithDocument} from "../../../../model/files/filePathWithDocument";
import {FilePath} from "../../../../model/files/filePath";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-file-explorer',
  standalone: true,
  imports: [
    NgIf,
    NgOptimizedImage,
    NgForOf
  ],
  templateUrl: './file-explorer.component.html',
  styleUrl: './file-explorer.component.css'
})
export class FileExplorerComponent {

  filePathService: FilePathService;
  currentDir: string = "";
  showGoToUpperDir =  false;

  childDirs: FilePathWithDocument[] = []
  childDocuments: FilePathWithDocument[] = []

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
    this.filePathService.getChildrenFromPath(new FilePath(dir), (children) => {
      this.setChildDirs(children);
      this.setChildDocuments(children);
    })
  }

  private setChildDirs(children: FilePathWithDocument[]){
    this.childDirs = children.filter(doc => doc.isDirectory());
  }

  private setChildDocuments(children: FilePathWithDocument[]){
    this.childDocuments = children.filter(doc => !doc.isDirectory());
  }

}
