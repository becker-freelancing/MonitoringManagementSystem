import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";

export class DocumentWithoutContent{
  documentId: number;
  pathToDocumentFromRoot: FilePath;
  documentName: string;
  fileType: FileType;


  constructor(documentId: number, pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType) {
    this.documentId = documentId;
    this.pathToDocumentFromRoot = pathToDocumentFromRoot;
    this.documentName = documentName;
    this.fileType = fileType;
  }
}
