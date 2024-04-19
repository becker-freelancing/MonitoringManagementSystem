import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCustomerTableComponent } from './all-customer-table.component';

describe('AllCustomerTableComponent', () => {
  let component: AllCustomerTableComponent;
  let fixture: ComponentFixture<AllCustomerTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllCustomerTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllCustomerTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
