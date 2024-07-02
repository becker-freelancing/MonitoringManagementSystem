import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";

export class Document {
  documentId: number;
  pathToDocumentFromRoot: FilePath;
  documentName: string;
  fileType: FileType;
  content: Int8Array;


  constructor(documentId: number, pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType, content: Int8Array) {
    this.documentId = documentId;
    this.pathToDocumentFromRoot = pathToDocumentFromRoot;
    this.documentName = documentName;
    this.fileType = fileType;
    this.content = content;
  }
}
