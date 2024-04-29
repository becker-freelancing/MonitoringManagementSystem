import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteProjectDialogComponent } from './delete-project-dialog.component';

describe('DeleteCustomerDialogComponent', () => {
  let component: DeleteProjectDialogComponent;
  let fixture: ComponentFixture<DeleteProjectDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteProjectDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteProjectDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
