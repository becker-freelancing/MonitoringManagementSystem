import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHoursOverviewComponent } from './working-hours-overview.component';

describe('WorkingHoursOverviewComponent', () => {
  let component: WorkingHoursOverviewComponent;
  let fixture: ComponentFixture<WorkingHoursOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHoursOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHoursOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
