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
    if (this.authentication.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate(["/index"]);
      return false;
    }
  }

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) {}
}
