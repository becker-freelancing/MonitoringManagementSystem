import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoManagementMenuBarComponent } from './todo-management-menu-bar.component';

describe('TodoManagementMenuBarComponent', () => {
  let component: TodoManagementMenuBarComponent;
  let fixture: ComponentFixture<TodoManagementMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoManagementMenuBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TodoManagementMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
