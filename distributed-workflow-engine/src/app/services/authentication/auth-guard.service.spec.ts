import { StompService } from 'ng2-stomp-service';
import { TestBed, inject } from '@angular/core/testing';

import { AuthGuardService } from './auth-guard.service';
import { AuthenticationService } from './authentication.service';
import { Http, HttpModule } from '@angular/http';
import { SocketService } from '../socket/socket.service';

describe('AuthGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [AuthGuardService, AuthenticationService, SocketService, StompService]
    });
  });

  it('should be created', inject([AuthGuardService], (service: AuthGuardService) => {
    expect(service).toBeTruthy();
  }));
});
