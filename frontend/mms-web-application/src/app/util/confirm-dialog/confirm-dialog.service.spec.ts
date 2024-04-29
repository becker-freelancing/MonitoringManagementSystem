import { TestBed } from '@angular/core/testing';

import { CofirmDialogServiceService } from './confirm-dialog.service';

describe('CofirmDialogServiceService', () => {
  let service: CofirmDialogServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CofirmDialogServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
