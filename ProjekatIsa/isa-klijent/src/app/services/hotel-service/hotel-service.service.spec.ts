import { TestBed } from '@angular/core/testing';

import { HotelServiceService } from './hotel-service.service';

describe('HotelServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HotelServiceService = TestBed.get(HotelServiceService);
    expect(service).toBeTruthy();
  });
});
