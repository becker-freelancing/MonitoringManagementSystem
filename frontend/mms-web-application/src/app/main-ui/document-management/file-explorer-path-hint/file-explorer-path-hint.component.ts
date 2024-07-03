import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {NgForOf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-file-explorer-path-hint',
  standalone: true,
  imports: [
    NgOptimizedImage,
    NgForOf
  ],
  templateUrl: './file-explorer-path-hint.component.html',
  styleUrl: './file-explorer-path-hint.component.css'
})
export class FileExplorerPathHintComponent implements OnChanges{

  @Input("filePath") filePath: string = "/";

  splittedFilePath: string[] = [];

  ngOnChanges(changes: SimpleChanges): void {
    if(changes["filePath"]){
      let newFilePath = changes['filePath'].currentValue;
      this.formatFilePath(newFilePath);
    }
  }

  private formatFilePath(newFilePath: string) {
    newFilePath = newFilePath.replace("root", "");
    this.splittedFilePath = newFilePath.split("\\");
  }
}
