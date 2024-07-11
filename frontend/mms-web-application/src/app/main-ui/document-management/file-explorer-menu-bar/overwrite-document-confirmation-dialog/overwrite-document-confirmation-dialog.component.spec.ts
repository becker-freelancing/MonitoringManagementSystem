import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OverwriteDocumentConfirmationDialogComponent} from './overwrite-document-confirmation-dialog.component';

describe('OverwriteDocumentConfirmationDialogComponent', () => {
  let component: OverwriteDocumentConfirmationDialogComponent;
  let fixture: ComponentFixture<OverwriteDocumentConfirmationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OverwriteDocumentConfirmationDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(OverwriteDocumentConfirmationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
