import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";

export class Document {
  documentId?: number;
  pathToDocumentFromRoot: FilePath;
  documentName: string;
  fileType: FileType;
  content: number[];


  constructor(pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType, content: number[], documentId?: number) {
    this.documentId = documentId;
    this.pathToDocumentFromRoot = pathToDocumentFromRoot;
    this.documentName = documentName;
    this.fileType = fileType;
    this.content = content;
  }

  fileEndingAsString(): string {
    return this.fileType.fileEnding;
  }
}
