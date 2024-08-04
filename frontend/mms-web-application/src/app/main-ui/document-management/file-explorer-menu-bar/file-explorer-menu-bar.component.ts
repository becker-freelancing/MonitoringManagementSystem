import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {NgClass, NgOptimizedImage} from "@angular/common";
import {FilePathWithDocument} from "../../../../model/files/filePathWithDocument";
import {MatDialog} from "@angular/material/dialog";
import {CreateDirectoryDialogComponent} from "./create-directory-dialog/create-directory-dialog.component";
import {FilePathService} from "../../../../services/files/filePathService";
import {FilePath} from "../../../../model/files/filePath";
import {CreateDocumentDialogComponent} from "./create-document-dialog/create-document-dialog.component";
import {Document} from "../../../../model/documents/Document";
import {DocumentsService} from "../../../../services/documents/documentsService";
import {DocumentWithoutContent} from "../../../../model/documents/DocumentWithoutContent";
import {DeleteDirectoryDialogComponent} from "./delete-directory-dialog/delete-directory-dialog.component";
import {DeleteDocumentDialogComponent} from "./delete-document-dialog/delete-document-dialog.component";
import {UploadDirectoryDialogComponent} from "./upload-directory-dialog/upload-directory-dialog.component";
import {
  OverwriteDocumentConfirmationDialogComponent
} from "./overwrite-document-confirmation-dialog/overwrite-document-confirmation-dialog.component";

@Component({
  selector: 'app-file-explorer-menu-bar',
  standalone: true,
  imports: [
    NgOptimizedImage,
    NgClass
  ],
  templateUrl: './file-explorer-menu-bar.component.html',
  styleUrl: './file-explorer-menu-bar.component.css'
})
export class FileExplorerMenuBarComponent implements OnChanges{

  @Input("document") document?: FilePathWithDocument;
  @Input("currentDir") currentDir!: string;
  @Output("update") updateEventEmitter = new EventEmitter<void>();

  enableDeleteDirectoryButton: boolean = false;
  enableDeleteDocumentButton: boolean = false;

  constructor(public dialog: MatDialog,
              public filePathService: FilePathService,
              public documentService: DocumentsService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["document"]){
      this.documentChanged();
    }
  }

  private documentChanged() {
    if(this.document){
      this.enableDeleteDirectoryButton = this.document.isDirectory();
      this.enableDeleteDocumentButton = !this.document.isDirectory();
    } else {
      this.enableDeleteDocumentButton = false;
      this.enableDeleteDirectoryButton = false;
    }
  }

  onCreateDirectory() {
    let dialogRef = this.dialog.open(CreateDirectoryDialogComponent, {
      width: '400px',
      height: '250px'
    });

    dialogRef.afterClosed().subscribe(dirName => {
      if (dirName === undefined) {
        return;
      }
      let toCreate = this.currentDir + "\\" + dirName;
      this.filePathService.createFileStructure(new FilePath(toCreate), (created) => this.update())
    })
  }

  onUploadDocument(){
    let dialogRef = this.dialog.open(CreateDocumentDialogComponent, {
      width: '500px',
      height: '350px'
    });

    dialogRef.afterClosed().subscribe(document => {

      this.existsDocument(document,
        () => this.showOverwriteConfirmationDialog(document.documentName, () => this.saveDocument(document)),
        () => this.saveDocument(document));
    })
  }

  onUploadFolder() {
    let dialogRef = this.dialog.open(UploadDirectoryDialogComponent, {
      width: '500px',
      height: '75%'
    });

    dialogRef.afterClosed().subscribe(documents => {
      for (const document of documents) {
        this.existsDocument(document,
          () => this.showOverwriteConfirmationDialog(document.documentName, () => this.saveDocument(document)),
          () => this.saveDocument(document));
      }
    })
  }


  showOverwriteConfirmationDialog(documentName: string, onOverwrite: () => void, onNotOverwrite?: () => void) {
    let dialogRef = this.dialog.open(OverwriteDocumentConfirmationDialogComponent, {
      width: '500px',
      height: '350px',
      data: {documentName: documentName}
    });

    dialogRef.afterClosed().subscribe(overwrite => {
      if (overwrite) {
        onOverwrite();
      } else if (onNotOverwrite) {
        onNotOverwrite();
      }
    })
  }

  existsDocument(document: any, onExists: () => void, onNotExists: () => void) {
    let toCheck = new DocumentWithoutContent(
      new FilePath(this.currentDir),
      document.documentName,
      document.fileType);

    this.documentService.existsDocument(toCheck, (exists) => {
      if(exists){
        onExists();
      } else {
        onNotExists();
      }
    })
  }

  saveDocument(document: any) {
    const reader = new FileReader();
    let that = this;
    reader.onload = function(e) {
      if(e !== null && e.target !== null && e.target.result instanceof ArrayBuffer && that.currentDir) {

        let fileDoc = new Document(
          new FilePath(that.currentDir + "\\" + that.extractFilePath(document)),
          document.documentName,
          document.fileType,
          Array.from(new Int8Array(e.target.result))
        );

        that.documentService.saveDocument(fileDoc, () => that.update());
      }
    };
    reader.readAsArrayBuffer(document.file);
  }


  private extractFilePath(document: any) {
    let relativePath = document.file.webkitRelativePath;
    let documentName = document.documentName + "." + document.fileType.fileEnding;
    if (!relativePath.includes(documentName)) {
      return relativePath;
    } else {
      return relativePath.replace(documentName, "");
    }
  }

  private update() {
    this.updateEventEmitter.emit();
  }

  deleteDirectory() {

    if(!this.document){
      return;
    }

    let dialogRef = this.dialog.open(DeleteDirectoryDialogComponent, {
      width: '500px',
      height: '375px',
      data: {dir: this.document?.filePath.filePath}
    });

    dialogRef.afterClosed().subscribe(canDelete => {
      if(canDelete && this.document){
        this.filePathService.deleteFileStructureWithChildren(new FilePath(this.document.filePath.filePath),
          (fileStructure) => this.update());
      }
    })
  }

  deleteDocument() {
    if (!this.document?.document) {
      return;
    }

    let dialogRef = this.dialog.open(DeleteDocumentDialogComponent, {
      width: '500px',
      height: '375px',
      data: {document: this.document.document}
    });

    dialogRef.afterClosed().subscribe(canDelete => {
      if (canDelete && this.document?.document) {
        this.documentService.deleteDocument(this.document.document,
          () => this.update())
      }
    })
  }
}
