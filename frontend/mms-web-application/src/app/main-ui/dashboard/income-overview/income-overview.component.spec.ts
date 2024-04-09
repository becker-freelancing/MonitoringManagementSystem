import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOverviewComponent } from './income-overview.component';

describe('IncomeOverviewComponent', () => {
  let component: IncomeOverviewComponent;
  let fixture: ComponentFixture<IncomeOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IncomeOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
