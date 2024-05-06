import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTodosOverviewComponent } from './all-todos-overview.component';

describe('AllTodosOverviewComponent', () => {
  let component: AllTodosOverviewComponent;
  let fixture: ComponentFixture<AllTodosOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllTodosOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllTodosOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
