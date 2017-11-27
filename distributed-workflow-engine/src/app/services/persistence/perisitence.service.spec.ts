import { TestBed, inject } from '@angular/core/testing';

import { PerisitenceService } from './perisitence.service';
import { HttpModule, Http } from '@angular/http';

describe('PerisitenceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [PerisitenceService]
    });
  });

  it('should be created', inject([PerisitenceService], (service: PerisitenceService) => {
    expect(service).toBeTruthy();
  }));
});
