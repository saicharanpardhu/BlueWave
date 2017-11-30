import { AuthenticationService } from "./authentication.service";
import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from "@angular/router";
import { Observable } from "rxjs/Observable";

@Injectable()
export class AuthGuardService implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> | Promise<boolean> {
    console.log("OnlyLoggedInUsers");
    if (this.authentication.isLoggedIn()) {
      console.log("Logged in.");
      return true;
    } else {
      console.log("You don't have permission to view this page");
      this.router.navigate(["/index"]);
      return false;
    }
  }

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) {}
}
