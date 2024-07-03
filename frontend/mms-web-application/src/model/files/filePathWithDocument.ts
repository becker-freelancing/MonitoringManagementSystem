import {FilePath} from "./filePath";
import {DocumentWithoutContent} from "../documents/DocumentWithoutContent";

export class FilePathWithDocument {
  filePath: FilePath;
  document?: DocumentWithoutContent;

  constructor(filePath: FilePath, document?: DocumentWithoutContent) {
    this.filePath = filePath;
    this.document = document;
  }

  isDirectory(){
    return this.document == undefined;
  }

  currentDirectoryName(){
    let split = this.filePath.filePath.split("\\")
    return split[split.length - 1];
  }
}
