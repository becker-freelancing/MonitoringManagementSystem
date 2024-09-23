import {Injectable} from "@angular/core";
import {HttpClient} from "../http/httpClient";
import {FileStructure} from "../../model/files/fileStructure";
import {FilePath} from "../../model/files/filePath";
import {FilePathWithDocument} from "../../model/files/filePathWithDocument";
import {DocumentWithoutContent} from "../../model/documents/DocumentWithoutContent";
import {FileType} from "../../model/files/fileType";
import {Tag} from "../../model/documents/tag";
import {DocumentWithoutContentResponseData} from "../documents/documentsService";

@Injectable({
  providedIn: 'root'
})
export class FilePathService {

  httpClient: HttpClient;


  constructor() {
    this.httpClient = new HttpClient();
  }

  getFileStructure(onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void) {
    this.httpClient.get('files/paths/filestructurewithchildren').then(r => {
      if (r.status != 200) {
        if (onError) {
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

  createFileStructure(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void) {
    this.httpClient.post('files/paths/filestructure', path).then(r => {
      if (r.status != 200) {
        if (onError) {
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

  deleteFileStructure(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void) {
    this.httpClient.delete('files/paths/filestructure', path).then(r => {
      if (r.status != 200) {
        if (onError) {
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

  deleteFileStructureWithChildren(path: FilePath, onSuccess: (fileStructure: FileStructure) => void, onError?: (errorCode: number) => void) {
    this.httpClient.post('files/paths/filestructurewithchildren', path).then(r => {
      if (r.status != 200) {
        if (onError) {
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

  getChildrenFromPath(path: FilePath, onSuccess: (children: FilePathWithDocument[]) => void, onError?: (errorCode: number) => void) {
    this.httpClient.post('files/paths/children', path).then(r => {
      if (r.status != 200) {
        if (onError) {
          onError(r.status);
        }
      }

      let responsePaths: FilePathWithDocument[] = [];

      for (let dataItem of r.data) {
        responsePaths.push(this.mapFilePathWithDocument(dataItem));
      }

      onSuccess(responsePaths);
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

  private mapFilePathWithDocument(data: FilePathWithDocumentResponseData) {
    if (data.document === null) {
      return new FilePathWithDocument(
        new FilePath(data.filePath),
        undefined
      );
    }
    let tags = new Set<Tag>();
    if (data.document.tags) {
      data.document.tags.forEach(val => tags.add(new Tag(val.tag)));
    }
    let documentWithoutContent = new DocumentWithoutContent(
      new FilePath(data.document.pathToDocumentFromRoot.filePath),
      data.document.documentName,
      new FileType(data.document.fileType.fileEnding),
      data.document.documentId,
      data.document.customerId,
      tags);
    return new FilePathWithDocument(
      new FilePath(data.filePath),
      documentWithoutContent
    );
  }
}

export interface FileStructureResponseData {
  current: string;
  children: FileStructureResponseData[];
}

export interface FilePathWithDocumentResponseData {
  filePath: string;
  document: DocumentWithoutContentResponseData | null;
}
