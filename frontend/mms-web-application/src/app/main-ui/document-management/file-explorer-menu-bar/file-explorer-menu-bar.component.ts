import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {NgClass, NgOptimizedImage} from "@angular/common";
import {FilePathWithDocument} from "../../../../model/files/filePathWithDocument";
import {
  AddCustomerDialogComponent
} from "../../customer-management/customer-management-menu-bar/add-customer-dialog/add-customer-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {CreateDirectoryDialogComponent} from "./create-directory-dialog/create-directory-dialog.component";
import {FilePathService} from "../../../../services/files/filePathService";
import {FileStructure} from "../../../../model/files/fileStructure";
import {FilePath} from "../../../../model/files/filePath";
import {
  CreateDocumentDialogComponent,
  CreateDocumentResultData
} from "./create-document-dialog/create-document-dialog.component";
import {Document} from "../../../../model/documents/Document";
import {DocumentsService} from "../../../../services/documents/documentsService";
import {DocumentWithoutContent} from "../../../../model/documents/DocumentWithoutContent";

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
        () => alert("Dokument existiert bereits"),
        () => this.saveDocument(document));
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
        console.log(e.target.result)

        let fileDoc = new Document(
          new FilePath(that.currentDir),
          document.documentName,
          document.fileType,
          Array.from(new Int8Array(e.target.result))
        );

        that.documentService.saveDocument(fileDoc, () => that.update());
      }
    };
    reader.readAsArrayBuffer(document.file);
  }


  private update() {
    this.updateEventEmitter.emit();
  }
}
