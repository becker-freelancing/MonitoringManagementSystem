import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileExplorerPathHintComponent } from './file-explorer-path-hint.component';

describe('FileExplorerPathHintComponent', () => {
  let component: FileExplorerPathHintComponent;
  let fixture: ComponentFixture<FileExplorerPathHintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FileExplorerPathHintComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FileExplorerPathHintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
