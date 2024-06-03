import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHourExistsWarningDialogComponent } from './working-hour-exists-warning-dialog.component';

describe('WorkingHourExistsWarningDialogComponent', () => {
  let component: WorkingHourExistsWarningDialogComponent;
  let fixture: ComponentFixture<WorkingHourExistsWarningDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHourExistsWarningDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHourExistsWarningDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
