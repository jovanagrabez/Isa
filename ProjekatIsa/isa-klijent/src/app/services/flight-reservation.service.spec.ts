import { TestBed } from '@angular/core/testing';

import { FlightReservationService } from './flight-reservation.service';

describe('FlightReservationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FlightReservationService = TestBed.get(FlightReservationService);
    expect(service).toBeTruthy();
  });
});
