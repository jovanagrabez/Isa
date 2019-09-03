import { TestBed } from '@angular/core/testing';

import { FilijaleServiceService } from './filijale-service.service';

describe('FilijaleServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FilijaleServiceService = TestBed.get(FilijaleServiceService);
    expect(service).toBeTruthy();
  });
});
