import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHoursOverviewChartComponent } from './working-hours-overview-chart.component';

describe('WorkingHoursOverviewChartComponent', () => {
  let component: WorkingHoursOverviewChartComponent;
  let fixture: ComponentFixture<WorkingHoursOverviewChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHoursOverviewChartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHoursOverviewChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
