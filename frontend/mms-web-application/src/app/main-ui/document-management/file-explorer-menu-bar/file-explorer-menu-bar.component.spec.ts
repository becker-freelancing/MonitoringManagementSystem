import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileExplorerMenuBarComponent } from './file-explorer-menu-bar.component';

describe('FileExplorerMenuBarComponent', () => {
  let component: FileExplorerMenuBarComponent;
  let fixture: ComponentFixture<FileExplorerMenuBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FileExplorerMenuBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FileExplorerMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
