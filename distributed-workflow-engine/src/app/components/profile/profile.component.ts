import { ProfileService } from "./../../services/profile/profile.service";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";
import { MatSnackBar } from "@angular/material";
@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private workflowService: WorkflowDetailsService,
    private profileService: ProfileService
  ) {}

  user: any = null;
  signUphide = true;
  reenterhide = true;
  username: string;
  loading=true;
  ngOnInit() {
    console.log("Triggering service..");
    this.statuscode = null;
    this.profileService
      .getUserDetails(JSON.parse(localStorage["loginData"])["Email"])
      .then(res => {
        this.loading = false;
        console.log(res.json());
        this.user = res.json();
        console.log(this.user);
      }).catch((err) => {
        // Handle any error that occurred in any of the previous
        console.error('I am the error in workflow',err);
        console.error(err.status);
        this.statuscode = err.status;
        this.loading = false;
        this.user = [];
        console.log(this.loading, "GOLA");
        this.snackBar.open('Cannot fetch data. Please try after some time','Close');
      });
  }

  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }

  checkValidation;

  signupSubmit = false;
  checkSubmitValidation() {
    this.signupSubmit =
      this.getEmailSignupErrorMessage() == "" &&
      this.getNameErrorMessage() == "";
    console.log(this.signupSubmit);
  }

  emailSignupValidator = new FormControl("", [
    Validators.required,
    Validators.email
  ]);
  getEmailSignupErrorMessage() {
    console.log(this.emailSignupValidator.hasError("required")
    ? "Email address required"
    : this.emailSignupValidator.hasError("email") ? "Not a valid email" : "");
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
  signup(firstName, lastName, email) {
    this.profileService
      .getUserDetails(JSON.parse(localStorage["loginData"])["Email"])
      .then(res => {
        let user = res.json();
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        this.profileService
          .update(user)
          .then(() =>
            this.snackBar.open("Successfully saved!", "Close", {
              duration: 2000
            })
          )
          .catch(() =>
            this.snackBar.open("Please try again later!", "Close", {
              duration: 2000
            })
          );
      });
  }

  passwordButton = false;
  checkLogin() {
    this.passwordButton = this.getPasswordErrorMessage() == "";
    console.log(this.passwordButton);
  }
  statuscode :any = null;
  updatePassword(reenterPassword, passwordSignup) {
    this.profileService
      .getUserDetails(JSON.parse(localStorage["loginData"])["Email"])
      .then(res => {
        let user = res.json();
        if (reenterPassword == user.password) {
          user.password = passwordSignup;
          console.log(user);
          this.profileService.update(user).then(() =>
            this.snackBar.open("Successfully saved!", "Close", {
              duration: 2000
            })
          );
        } else {
          this.snackBar.open("Please enter the correct password", "Close");
        }
      }).catch((err) => {
        // Handle any error that occurred in any of the previous
        console.error('I am the error in workflow',err);
        console.error(err.status);
        this.statuscode = err.status;
        this.loading = true;
        this.snackBar.open('Cannot fetch data. Please try after some time','Close');
      });
  }
}
