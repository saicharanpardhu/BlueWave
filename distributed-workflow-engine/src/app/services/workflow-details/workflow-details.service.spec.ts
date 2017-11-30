import { TestBed, inject } from '@angular/core/testing';

import { WorkflowDetailsService } from './workflow-details.service';
import { SocketService } from '../socket/socket.service';
import { Http, HttpModule } from '@angular/http';
import { AppConfig } from '../../app.config';

describe('WorkflowDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers:   [WorkflowDetailsService, AppConfig]
    });
  });

  it('should be created', inject([WorkflowDetailsService], (service: WorkflowDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
