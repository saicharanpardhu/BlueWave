import { TestBed, inject } from '@angular/core/testing';

import { PerisitenceService } from './perisitence.service';
import { HttpModule, Http } from '@angular/http';
import { AppConfig } from '../../app.config';

describe('PerisitenceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [PerisitenceService, AppConfig]
    });
  });

  it('should be created', inject([PerisitenceService], (service: PerisitenceService) => {
    expect(service).toBeTruthy();
  }));
});
