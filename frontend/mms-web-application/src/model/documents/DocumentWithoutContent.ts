import {FilePath} from "../files/filePath";
import {FileType} from "../files/fileType";
import {Tag} from "./tag";

export class DocumentWithoutContent{

  pathToDocumentFromRoot: FilePath;
  documentName: string;
  fileType: FileType;
  documentId?: number;
  customerId?: number;
  tags: Tag[];


  constructor(pathToDocumentFromRoot: FilePath, documentName: string, fileType: FileType, documentId?: number, customerId?: number, tags?: Tag[]) {
    this.pathToDocumentFromRoot = pathToDocumentFromRoot;
    this.documentName = documentName;
    this.fileType = fileType;
    this.documentId = documentId;
    this.customerId = customerId;
    this.tags = tags ? tags : [];
  }
}
