import {FilePath} from "./filePath";
import {DocumentWithoutContent} from "../documents/DocumentWithoutContent";

export class FilePathWithDocument {
  filePath: FilePath;
  document?: DocumentWithoutContent;

  constructor(filePath: FilePath, document: DocumentWithoutContent) {
    this.filePath = filePath;
    this.document = document;
  }
}
