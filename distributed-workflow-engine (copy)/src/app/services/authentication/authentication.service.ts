import { SocketService } from "./../socket/socket.service";
import { Injectable } from "@angular/core";
import { Headers, Http } from "@angular/http";
import { MatSnackBar, MatSnackBarConfig } from "@angular/material";
import { Router } from "@angular/router";
import { AppConfig } from "../../app.config";

@Injectable()
export class AuthenticationService {
  statuscode: any;
  public logout() {
    localStorage.clear();
    localStorage.removeItem("Email");
    localStorage.removeItem("loginData");
  }
  constructor(
    private http: Http,
    private socket: SocketService,
    private snackBar: MatSnackBar,
    private router: Router,
    private config: AppConfig
  ) {}
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  isLoggedIn() {
    // return true;
    return !(localStorage["loginData"] == null);
  }

  signup(firstName, lastName, email, userName, password) {
    let json = JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      email: email,
      userName: userName,
      password: password
    });
    console.log(json);
    return this.http
      .post(this.config.userSignup, json, { headers: this.headers })
      .toPromise()
      .then(response => console.log(response));
  }

  login(username, password) {
    let json = "grant_type=password&password=akshay&username=akshay";
    let body = new URLSearchParams();
    body.set("username", "akshay");
    body.set("password", "akshay");
    body.set("grant_type", "password");
    let headers = new Headers();
    headers.append(
      "Authorization",
      "Basic Zm9vQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ="
    );
    return this.http
      .post(
        this.config.login +
          "/oauth/token?grant_type=password&username=" +
          username +
          "&password=" +
          password,
        json,
        { headers: headers }
      )
      .toPromise()
      .then(response => {
        localStorage.setItem(
          "loginData",
          JSON.stringify({
            access_token: response.json().access_token,
            refresh_token: response.json().refresh_token,
            Email: username
          })
        );
        this.socket.connect();
        localStorage.setItem("Email", username);
        this.router.navigate(["/home"]);
        console.log(localStorage.getItem("loginData"));
      }).catch((err) => {
        // Handle any error that occurred in any of the previous
        console.error('I am the error of auth',err);
        console.error(err.status);
        console.error(err._body);
        this.statuscode = err.status;
        this.snackBar.open("Network Connection Error. Please try after sometime.",'close');
      })
      .catch(() => {
        let config = new MatSnackBarConfig();
        config.duration = 1000;
        this.snackBar.open("Wrong username/password.", "", config);
      });
  }

  checkUsername(username) {
    return this.http.get("http://172.23.238.176:8088/v0.1/userinfo/user");
  }
}
