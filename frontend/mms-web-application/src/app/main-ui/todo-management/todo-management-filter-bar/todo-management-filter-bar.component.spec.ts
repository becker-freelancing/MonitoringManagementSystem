import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoManagementFilterBarComponent } from './todo-management-filter-bar.component';

describe('TodoManagementFilterBarComponent', () => {
  let component: TodoManagementFilterBarComponent;
  let fixture: ComponentFixture<TodoManagementFilterBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoManagementFilterBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TodoManagementFilterBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
