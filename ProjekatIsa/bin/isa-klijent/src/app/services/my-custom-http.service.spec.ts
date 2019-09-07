import { TestBed } from '@angular/core/testing';

import { MyCustomHttpService } from './my-custom-http.service';

describe('MyCustomHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MyCustomHttpService = TestBed.get(MyCustomHttpService);
    expect(service).toBeTruthy();
  });
});
