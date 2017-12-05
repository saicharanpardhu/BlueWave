import { TestBed, inject } from '@angular/core/testing';

import { SocketService } from './socket.service';
import { Http, HttpModule } from '@angular/http';
import { AppConfig } from '../../app.config';

describe('SocketService', () => {
  class SocketServiceStub extends SocketService{

  }
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpModule],
      providers: [AppConfig, { provide: SocketService, useValue: SocketServiceStub}]
    });
  }); 

  it('should be created', inject([SocketService], (service: SocketService) => {
    expect(service).toBeTruthy();
  }));
});
