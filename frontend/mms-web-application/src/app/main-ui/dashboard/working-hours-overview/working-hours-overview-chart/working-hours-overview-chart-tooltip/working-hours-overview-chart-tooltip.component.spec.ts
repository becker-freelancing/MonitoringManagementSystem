import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHoursOverviewChartTooltipComponent } from './working-hours-overview-chart-tooltip.component';

describe('WorkingHoursOverviewChartTooltipComponent', () => {
  let component: WorkingHoursOverviewChartTooltipComponent;
  let fixture: ComponentFixture<WorkingHoursOverviewChartTooltipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHoursOverviewChartTooltipComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHoursOverviewChartTooltipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
