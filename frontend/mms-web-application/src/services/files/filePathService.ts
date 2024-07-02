import {Injectable} from "@angular/core";
import {HttpClient} from "../http/httpClient";
import {FileStructure} from "../../model/files/fileStructure";
import {FilePath} from "../../model/files/filePath";

@Injectable({
  providedIn: 'root'
})
export class FilePathService{

  httpClient: HttpClient;


  constructor() {
    this.httpClient = new HttpClient();
  }

  getFileStructure(onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void){
    this.httpClient.get('files/paths/filestructurewithchildren').then(r => {
      if(r.status != 200){
        if(onError){
          onError(r.status);
        }
      }

      onSuccess(this.mapFileStructure(r.data));
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  createFileStructure(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void){
    this.httpClient.post('files/paths/filestructure', path).then(r => {
      if(r.status != 200){
        if(onError){
          onError(r.status);
        }
      }

      onSuccess(this.mapFileStructure(r.data));
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  deleteFileStructure(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void){
    this.httpClient.delete('files/paths/filestructure', path).then(r => {
      if(r.status != 200){
        if(onError){
          onError(r.status);
        }
      }

      onSuccess(this.mapFileStructure(r.data));
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  deleteFileStructureWithChildren(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void){
    this.httpClient.post('files/paths/filestructurewithchildren', path).then(r => {
      if(r.status != 200){
        if(onError){
          onError(r.status);
        }
      }

      onSuccess(this.mapFileStructure(r.data));
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  getChildrenFromPath(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void){
    this.httpClient.post('files/paths/children', path).then(r => {
      if(r.status != 200){
        if(onError){
          onError(r.status);
        }
      }

      onSuccess(this.mapFileStructure(r.data));
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  private mapFileStructure(data: FileStructureResponseData) {
    let fileStructure = new FileStructure(data.current);
    for (const child of data.children) {
      fileStructure.addChildren(this.mapFileStructure(child));
    }
    return fileStructure;
  }
}

export interface FileStructureResponseData {
  current: string;
  children: FileStructureResponseData[];
}
