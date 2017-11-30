import { TestBed, inject } from '@angular/core/testing';

import { ReportService } from './report.service';
import { Http, HttpModule } from '@angular/http';
import { AppConfig } from '../../app.config';

describe('ReportService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [ReportService, AppConfig]
    });
  });

  it('should be created', inject([ReportService], (service: ReportService) => {
    expect(service).toBeTruthy();
  }));
});
