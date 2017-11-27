import { TestBed, inject } from '@angular/core/testing';

import { ReportService } from './report.service';
import { Http, HttpModule } from '@angular/http';

describe('ReportService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [ReportService]
    });
  });

  it('should be created', inject([ReportService], (service: ReportService) => {
    expect(service).toBeTruthy();
  }));
});
