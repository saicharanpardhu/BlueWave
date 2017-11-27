import { MatSnackBar } from '@angular/material';
import { TestBed, inject } from "@angular/core/testing";

import { AuthenticationService } from "./authentication.service";
import { Http, HttpModule } from "@angular/http";
import { SocketService } from "../socket/socket.service";
import { StompService } from "ng2-stomp-service/dist/stomp.service";
import { AppConfig } from "../../app.config";
import { OVERLAY_PROVIDERS } from '@angular/cdk/overlay';

describe("AuthenticationService", () => {
  class SocketServiceStub extends SocketService {}
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [
        AuthenticationService,
        AppConfig,
        { provide: SocketService, useValue: SocketServiceStub },
        StompService,
        MatSnackBar,
        OVERLAY_PROVIDERS
      ]
    });
  });

  it(
    "should be created",
    inject([AuthenticationService], (service: AuthenticationService) => {
      expect(service).toBeTruthy();
    })
  );
});
