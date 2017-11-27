import { MatSnackBar } from '@angular/material';
import { StompService } from "ng2-stomp-service";
import { TestBed, inject } from "@angular/core/testing";

import { AuthGuardService } from "./auth-guard.service";
import { AuthenticationService } from "./authentication.service";
import { Http, HttpModule } from "@angular/http";
import { SocketService } from "../socket/socket.service";
import { AppConfig } from "../../app.config";

describe("AuthGuardService", () => {

  class SocketServiceStub extends SocketService{
    
  }
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [
        AuthGuardService,
        AppConfig,
        AuthenticationService,
        {provide: SocketService, useValue: SocketServiceStub },
        StompService,
        MatSnackBar
      ]
    });
  });

  it(
    "should be created",
    inject([AuthGuardService], (service: AuthGuardService) => {
      expect(service).toBeTruthy();
    })
  );
});
