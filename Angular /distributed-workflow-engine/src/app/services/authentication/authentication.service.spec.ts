import { MatSnackBar } from '@angular/material';
import { TestBed, inject } from "@angular/core/testing";

import { AuthenticationService } from "./authentication.service";
import { Http, HttpModule } from "@angular/http";
import { AppConfig } from "../../app.config";
import { OVERLAY_PROVIDERS } from '@angular/cdk/overlay';

describe("AuthenticationService", () => {
  class AuthenticationServiceStub extends AuthenticationService{
    isLoggedIn(){
      return true;
    }
  };
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [
        {provide: AuthenticationService, useValue: AuthenticationServiceStub},
        AppConfig,
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
