import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerManagementMenuBarComponent } from './customer-management-menu-bar.component';

describe('CustomerManagementMenuBarComponent', () => {
  let component: CustomerManagementMenuBarComponent;
  let fixture: ComponentFixture<CustomerManagementMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerManagementMenuBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomerManagementMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
