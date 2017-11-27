import { TestBed, inject } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { Http, HttpModule } from '@angular/http';
import { SocketService } from '../socket/socket.service';
import { StompService } from 'ng2-stomp-service/dist/stomp.service';

describe('AuthenticationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [AuthenticationService, SocketService, StompService]
    });
  });

  it('should be created', inject([AuthenticationService], (service: AuthenticationService) => {
    expect(service).toBeTruthy();
  }));
});
