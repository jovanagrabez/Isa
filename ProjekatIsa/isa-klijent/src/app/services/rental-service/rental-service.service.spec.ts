import { TestBed } from '@angular/core/testing';

import { RentalServiceService } from './rental-service.service';

describe('RentalServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentalServiceService = TestBed.get(RentalServiceService);
    expect(service).toBeTruthy();
  });
});
