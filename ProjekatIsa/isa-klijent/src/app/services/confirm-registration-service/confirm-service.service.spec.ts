import { TestBed } from '@angular/core/testing';

import { ConfirmServiceService } from './confirm-service.service';

describe('ConfirmServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConfirmServiceService = TestBed.get(ConfirmServiceService);
    expect(service).toBeTruthy();
  });
});
