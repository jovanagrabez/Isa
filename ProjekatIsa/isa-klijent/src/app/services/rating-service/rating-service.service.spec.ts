import { TestBed } from '@angular/core/testing';

import { RatingServiceService } from './rating-service.service';

describe('RatingServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RatingServiceService = TestBed.get(RatingServiceService);
    expect(service).toBeTruthy();
  });
});
