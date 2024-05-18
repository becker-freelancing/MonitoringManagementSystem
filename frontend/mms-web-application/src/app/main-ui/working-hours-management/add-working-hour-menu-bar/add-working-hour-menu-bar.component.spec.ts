import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkingHourMenuBarComponent } from './add-working-hour-menu-bar.component';

describe('AddWorkingHourMenuBarComponent', () => {
  let component: AddWorkingHourMenuBarComponent;
  let fixture: ComponentFixture<AddWorkingHourMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddWorkingHourMenuBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddWorkingHourMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
