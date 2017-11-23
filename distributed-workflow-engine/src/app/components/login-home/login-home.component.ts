import { AuthenticationService } from './../../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core'; 
import { FormControl, Validators, FormBuilder } from '@angular/forms';
import { SocketService } from './../../services/socket/socket.service';
 
@Component({
  selector: 'app-login-home',
  templateUrl: './login-home.component.html',
  styleUrls: ['./login-home.component.css']
})
export class LoginHomeComponent implements OnInit {
  constructor(private router: Router, 
    private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private socket: SocketService
  ) { }
  public user:any;
  ngOnInit() {
    // console.log("Logged in: ", !(this.authenticationService.getAccessToken() === ''));
    // if(!(this.authenticationService.getAccessToken() === '')){
    //   this.router.navigate(['/home']);
    // }
    // this.socket.showUsername();
    
  }
  emailLoginValidator = new FormControl('', [Validators.required]); 
  getEmailLoginErrorMessage() {
    
    return this.emailLoginValidator.hasError('required') ? 'Username required' :'';
  }
  usernameValidator = new FormControl('', [Validators.required]); 
  getUsernameErrorMessage() { 
    return this.usernameValidator.hasError('required') ? 'Username required' : '';
  } 
  emailSignupValidator = new FormControl('', [Validators.required, Validators.email]); 
  getEmailSignupErrorMessage() {
    
    return this.emailSignupValidator.hasError('required') ? 'Email address required' :
        this.emailSignupValidator.hasError('email') ? 'Not a valid email' :
            '';
  }
  nameValidator = new FormControl('', [Validators.required]);
  getNameErrorMessage() {
    
    return this.nameValidator.hasError('required') ? 'You must enter both first and last name.':'';
  }
  passwordValidator = new FormControl('', [Validators.required]);
  getPasswordErrorMessage() {
    
    return this.passwordValidator.hasError('required') ? 'Password is required.':'';
  }
  login(email,password){
    this.authenticationService.login(email,password);
    // if(email){
    //   email = JSON.stringify(email);
    // } 
    // console.log("Authenticated from login", this.authenticationService.getAccessToken());  
  }
  
  signup(firstName, lastName, userName, email, password){
    this.authenticationService.signup(firstName, lastName, userName, email, password).then(()=>{
      this.login(email,password); 
    });
  }
  hide = true;
  signUphide = true;
}