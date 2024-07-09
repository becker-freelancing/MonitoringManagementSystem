import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";

export class DocumentWithoutContent{

  pathToDocumentFromRoot: FilePath;
  documentName: string;
  fileType: FileType;
  documentId?: number;


  constructor(pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType, documentId?: number) {
    this.documentId = documentId;
    this.pathToDocumentFromRoot = pathToDocumentFromRoot;
    this.documentName = documentName;
    this.fileType = fileType;
  }
}
