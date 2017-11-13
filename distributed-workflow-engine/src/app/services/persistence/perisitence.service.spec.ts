import { TestBed, inject } from '@angular/core/testing';

import { PerisitenceService } from './perisitence.service';

describe('PerisitenceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PerisitenceService]
    });
  });

  it('should be created', inject([PerisitenceService], (service: PerisitenceService) => {
    expect(service).toBeTruthy();
  }));
});
