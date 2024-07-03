import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDirectoryDialogComponent } from './create-directory-dialog.component';

describe('CreateDirectoryDialogComponent', () => {
  let component: CreateDirectoryDialogComponent;
  let fixture: ComponentFixture<CreateDirectoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateDirectoryDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateDirectoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
