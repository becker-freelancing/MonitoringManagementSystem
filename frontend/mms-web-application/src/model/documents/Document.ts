import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";
import {DocumentWithoutContent} from "./DocumentWithoutContent";
import {Tag} from "./tag";

export class Document extends DocumentWithoutContent {

  content: number[];


  constructor(pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType, content: number[], documentId?: number, customerId?: number, tags?: Tag[]) {
    super(pathToDocumentFromRoot, documentName, fileType, documentId, customerId, tags);
    this.content = content;
  }

  fileEndingAsString(): string {
    return this.fileType.fileEnding;
  }
}
