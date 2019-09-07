import { TestBed } from '@angular/core/testing';

import { ViewRentalCarsService } from './view-rental-cars.service';

describe('ViewRentalCarsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewRentalCarsService = TestBed.get(ViewRentalCarsService);
    expect(service).toBeTruthy();
  });
});
