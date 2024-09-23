import {Injectable} from "@angular/core";
import {HttpClient} from "../http/httpClient";
import {Document} from "../../model/documents/Document";
import {FilePath} from "../../model/files/filePath";
import {FileType} from "../../model/files/fileType";
import {DocumentWithoutContent} from "../../model/documents/DocumentWithoutContent";
import {Tag} from "../../model/documents/tag";

@Injectable({
  providedIn: 'root'
})
export class DocumentsService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  saveDocument(document: Document, onSuccess: (document: Document) => void, onError?: (errorCode: number) => void){

    this.httpClient.post('documents/save', document).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToDocument(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }});
  }

  existsDocument(document: DocumentWithoutContent, onSuccess: (exists: boolean) => void, onError?: (errorCode: number) => void){
    this.httpClient.post('documents/exists', document).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(r.data);
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }});
  }

  getDocument(id: number, onSuccess: (document: Document) => void, onError?: (errorCode: number) => void){

    this.httpClient.get('documents/' + id).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToDocument(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }});
  }

  getDocumentWithoutContent(id: number, onSuccess: (document: DocumentWithoutContent) => void, onError?: (errorCode: number) => void) {

    this.httpClient.get('documents/withoutcontent/' + id).then(r => {
      if (r.status != 200) {
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToDocumentWithoutContent(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }
    });
  }

  deleteDocument(document: DocumentWithoutContent, onSuccess: (document: Document) => void, onError?: (errorCode: number) => void){

    this.httpClient.deletePost('documents', document).then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToDocument(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }});
  }

  addTag(documentId: number, tag: string, onSuccess: (document: Document) => void, onError?: (errorCode: number) => void) {
    this.httpClient.post("documents/tags", {documentId: documentId, tag: tag}).then(r => {
      if (r.status != 200) {
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(this.mapToDocument(r.data));
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }
    });
  }

  private mapToDocument(data: DocumentResponseData) {
    let tags = new Set<Tag>();
    if (data.tags) {
      data.tags.forEach(val => tags.add(this.mapToTag(val)));
    }
    return new Document(
      this.mapToFilePath(data.pathToDocumentFromRoot),
      data.documentName,
      new FileType(data.fileType.fileEnding),
      data.content,
      data.documentId,
      data.customerId,
      tags)
  }

  private mapToDocumentWithoutContent(data: DocumentWithoutContentResponseData) {
    let tags = new Set<Tag>();
    data.tags.forEach(val => tags.add(this.mapToTag(val)))
    return new DocumentWithoutContent(
      this.mapToFilePath(data.pathToDocumentFromRoot),
      data.documentName,
      new FileType(data.fileType.fileEnding),
      data.documentId,
      data.customerId,
      tags)
  }

  private mapToTag(data: TagResponseData) {
    return new Tag(data.tag);
  }

  private mapToFilePath(data: FilePathResponseData){
    return new FilePath(data.filePath);
  }
}

export interface DocumentResponseData extends DocumentWithoutContentResponseData {
  content: number[];
}

export interface DocumentWithoutContentResponseData {
  documentId: number;
  pathToDocumentFromRoot: FilePathResponseData;
  documentName: string;
  fileType: { fileEnding: string };
  customerId: number;
  tags: TagResponseData[]
}

export interface FilePathResponseData {
  filePath:	string;
}

export interface TagResponseData {
  tag: string;
}
