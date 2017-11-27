import { StompService } from 'ng2-stomp-service';
import { TestBed, inject } from '@angular/core/testing';

import { SocketService } from './socket.service';
import { Http, HttpModule } from '@angular/http';
import { AppConfig } from '../../app.config';

describe('SocketService', () => {
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [AppConfig, SocketService, StompService]
    });
  });

  it('should be created', inject([SocketService], (service: SocketService) => {
    expect(service).toBeTruthy();
  }));
});
