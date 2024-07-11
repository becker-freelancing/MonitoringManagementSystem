import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UploadDirectoryDialogComponent} from './upload-directory-dialog.component';

describe('UploadDirectoryDialogComponent', () => {
  let component: UploadDirectoryDialogComponent;
  let fixture: ComponentFixture<UploadDirectoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadDirectoryDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UploadDirectoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
