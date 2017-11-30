import { MatSnackBar } from '@angular/material';
import { TestBed, inject } from "@angular/core/testing";

import { AuthGuardService } from "./auth-guard.service";
import { AuthenticationService } from "./authentication.service";
import { Http, HttpModule } from "@angular/http";
import { AppConfig } from "../../app.config";

describe("AuthGuardService", () => {
  class AuthenticationServiceStub extends AuthenticationService{};
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [
        AuthGuardService,
        AppConfig,
        {provide: AuthenticationService, useValue: AuthenticationServiceStub},
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
