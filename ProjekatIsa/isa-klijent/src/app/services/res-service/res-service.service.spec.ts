import { TestBed } from '@angular/core/testing';

import { ResServiceService } from './res-service.service';

describe('ResServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ResServiceService = TestBed.get(ResServiceService);
    expect(service).toBeTruthy();
  });
});
