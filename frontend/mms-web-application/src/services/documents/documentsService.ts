import {Injectable} from "@angular/core";
import {HttpClient} from "../http/httpClient";
import {Document} from "../../model/documents/Document";
import {FilePath} from "../../model/files/filePath";
import {FileType} from "../../model/files/fileType";
import {DocumentWithoutContent} from "../../model/documents/DocumentWithoutContent";

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

    this.httpClient.get('documents/save/' + id).then(r =>{
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

  private mapToDocument(data: DocumentResponseData) {
    return new Document(
      this.mapToFilePath(data.pathToDocumentFromRoot),
      data.documentName,
      new FileType(data.fileType),
      data.content,
      data.documentId)
  }

  private mapToFilePath(data: FilePathResponseData){
    return new FilePath(data.filePath);
  }
}

export interface DocumentResponseData {
  documentId: number;
  pathToDocumentFromRoot: FilePathResponseData;
  documentName: string;
  fileType: string;
  content: number[];
}

export interface FilePathResponseData {
  filePath:	string;
}
