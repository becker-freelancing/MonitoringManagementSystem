import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllProjectsTableComponent } from './all-projects-table.component';

describe('AllCustomerTableComponent', () => {
  let component: AllProjectsTableComponent;
  let fixture: ComponentFixture<AllProjectsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllProjectsTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllProjectsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
