import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DocumentsService} from "../../../../services/documents/documentsService";
import {DocumentWithoutContent} from "../../../../model/documents/DocumentWithoutContent";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-document-edit',
  standalone: true,
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './document-edit.component.html',
  styleUrl: './document-edit.component.css'
})
export class DocumentEditComponent implements OnInit {

  documentId!: number;
  documentWithoutContent!: DocumentWithoutContent;

  @ViewChild("addTagTagNameTextInput") addTagTagNameTextInput!: ElementRef;

  constructor(private route: ActivatedRoute,
              private documentsService: DocumentsService) {
  }

  ngOnInit() {
    this.documentId = +this.route.snapshot.paramMap.get('id')!;
    this.documentsService.getDocumentWithoutContent(this.documentId, doc => this.documentWithoutContent = doc)


  }

  addTag() {
    let tag = this.addTagTagNameTextInput.nativeElement.value;

    this.documentsService.addTag(this.documentId, tag, (doc) => this.documentWithoutContent = doc);
  }
}
