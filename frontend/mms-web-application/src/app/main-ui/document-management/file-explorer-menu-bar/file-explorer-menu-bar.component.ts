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
  @Input("currentDir") currentDir?: string;
  @Output("update") updateEventEmitter = new EventEmitter<void>();

  enableDeleteDirectoryButton: boolean = false;
  enableDeleteDocumentButton: boolean = false;

  constructor(public dialog: MatDialog,
              public filePathService: FilePathService) {
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



  private update() {
    this.updateEventEmitter.emit();
  }
}
