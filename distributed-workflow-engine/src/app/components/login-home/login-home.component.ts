import { AuthenticationService } from "./../../services/authentication/authentication.service";
import { Router } from "@angular/router";
import { Component, OnInit, Inject } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";
import { SocketService } from "./../../services/socket/socket.service";
import { MatSnackBar } from "@angular/material";
import { SlimLoadingBarService } from "ng2-slim-loading-bar";

@Component({
  selector: "app-login-home",
  templateUrl: "./login-home.component.html",
  styleUrls: ["./login-home.component.css"]
})
export class LoginHomeComponent implements OnInit {
  loginForm: FormGroup;
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private socket: SocketService,
    private snackBar: MatSnackBar,
    private slimLoadingBarService: SlimLoadingBarService
  ) {}

  public user: any;
  hide = true;
  signUphide = true;

  ngOnInit() {
      if(this.authenticationService.isLoggedIn()) this.router.navigate(['/home']);
  }

  loginSubmit = false;
  signupSubmit = false;
  checkLoginValidation() {
    this.loginSubmit =
      this.getEmailLoginErrorMessage() == "" &&
      this.getPasswordLoginErrorMessage() == "";
  }

  checkSubmitValidation() {
    this.signupSubmit =
      this.getUsernameErrorMessage() == "" &&
      this.getEmailSignupErrorMessage() == "" &&
      this.getNameErrorMessage() == "" &&
      this.getPasswordErrorMessage() == "";
  }
  emailLoginValidator = new FormControl("", [
    Validators.required,
    Validators.minLength(6)
  ]);
  getEmailLoginErrorMessage() {
    return this.emailLoginValidator.hasError("required")
      ? "Username required"
      : this.emailLoginValidator.hasError("minlength")
        ? "Minimum username length is 6"
        : "";
  }

  usernameValidator = new FormControl("", [
    Validators.required,
    Validators.minLength(6)
  ]);
  getUsernameErrorMessage() {
    return this.usernameValidator.hasError("required")
      ? "Username required"
      : this.usernameValidator.hasError("minlength")
        ? "Minimum username length is 6"
        : "";
  }

  checkValidation;

  emailSignupValidator = new FormControl("", [
    Validators.required,
    Validators.email
  ]);
  getEmailSignupErrorMessage() {
    return this.emailSignupValidator.hasError("required")
      ? "Email address required"
      : this.emailSignupValidator.hasError("email") ? "Not a valid email" : "";
  }

  nameValidator = new FormControl("", [
    Validators.required,
    Validators.minLength(2)
  ]);
  getNameErrorMessage() {
    return this.nameValidator.hasError("required")
      ? "You must enter both first and last name."
      : this.nameValidator.hasError("minlength")
        ? "Minimum name length is 4"
        : "";
  }
  passwordValidator = new FormControl("", [
    Validators.required,
    Validators.minLength(6)
  ]);
  getPasswordErrorMessage() {
    return this.passwordValidator.hasError("required")
      ? "Password is required."
      : this.passwordValidator.hasError("minlength")
        ? "Minimum password length is 6"
        : "";
  }

  passwordLoginValidator = new FormControl("", [
    Validators.required,
    Validators.minLength(6)
  ]);
  getPasswordLoginErrorMessage() {
    return this.passwordLoginValidator.hasError("required")
      ? "Password is required."
      : this.passwordLoginValidator.hasError("minlength")
        ? "Minimum password length is 6"
        : "";
  }

  login(email, password) {
    this.slimLoadingBarService.interval = 1000;
    this.slimLoadingBarService.progress = 25;
    
    this.authenticationService.login(email, password);
  }
  statuscode: any;
  signup(firstName, lastName, userName, email, password) {
    this.authenticationService
      .signup(firstName, lastName, userName, email, password)
      .then(() => {
        this.login(email, password);
      }).catch((err) => {
        // Handle any error that occurred in any of the previous
        console.error('I am the error of userexist',err);
        console.error(err.status);
        console.error(err._body);
        this.statuscode = err.status;
        if(this.statuscode == 0){
          this.snackBar.open('Cannot signup right now. Please check credentials or try again later.','Close');
        }else{
          this.snackBar.open(err._body,'Close');
        }
      });
  }
}
