import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllWorkingHoursOverviewComponent } from './all-working-hours-overview.component';

describe('AllWorkingHoursOverviewComponent', () => {
  let component: AllWorkingHoursOverviewComponent;
  let fixture: ComponentFixture<AllWorkingHoursOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllWorkingHoursOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllWorkingHoursOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
