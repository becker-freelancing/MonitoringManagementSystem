import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDirectoryDialogComponent } from './delete-directory-dialog.component';

describe('DeleteDirectoryDialogComponent', () => {
  let component: DeleteDirectoryDialogComponent;
  let fixture: ComponentFixture<DeleteDirectoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteDirectoryDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteDirectoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
