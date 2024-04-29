import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectManagementMenuBarComponent } from './project-management-menu-bar.component';

describe('CustomerManagementMenuBarComponent', () => {
  let component: ProjectManagementMenuBarComponent;
  let fixture: ComponentFixture<ProjectManagementMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectManagementMenuBarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectManagementMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
