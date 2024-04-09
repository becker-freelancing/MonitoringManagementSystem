import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHoursManagementComponent } from './working-hours-management.component';

describe('WorkingHoursManagementComponent', () => {
  let component: WorkingHoursManagementComponent;
  let fixture: ComponentFixture<WorkingHoursManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHoursManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHoursManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
