import { TestBed } from '@angular/core/testing';

import { AviocompanySService } from './aviocompany-s.service';

describe('AviocompanySService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AviocompanySService = TestBed.get(AviocompanySService);
    expect(service).toBeTruthy();
  });
});
