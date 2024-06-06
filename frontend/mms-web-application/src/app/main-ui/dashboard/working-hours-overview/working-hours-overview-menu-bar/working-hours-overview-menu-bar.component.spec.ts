import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkingHoursOverviewMenuBarComponent } from './working-hours-overview-menu-bar.component';

describe('WorkingHoursOverviewMenuBarComponent', () => {
  let component: WorkingHoursOverviewMenuBarComponent;
  let fixture: ComponentFixture<WorkingHoursOverviewMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkingHoursOverviewMenuBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkingHoursOverviewMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
