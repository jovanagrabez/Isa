import { TestBed } from '@angular/core/testing';

import { ViewHotelsService } from './view-hotels.service';

describe('ViewHotelsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewHotelsService = TestBed.get(ViewHotelsService);
    expect(service).toBeTruthy();
  });
});
