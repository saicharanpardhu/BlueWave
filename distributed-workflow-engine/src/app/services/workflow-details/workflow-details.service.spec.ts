import { TestBed, inject } from '@angular/core/testing';

import { WorkflowDetailsService } from './workflow-details.service';

describe('WorkflowDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorkflowDetailsService]
    });
  });

  it('should be created', inject([WorkflowDetailsService], (service: WorkflowDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
