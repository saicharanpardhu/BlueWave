import { SocketService } from './../socket/socket.service';
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';

@Injectable()
export class AuthenticationService {

  private access_token : String = '';
  private refresh_token : String = '';

  public getAccessToken(){
    return this.access_token;
  }
  public setAccessToken(){
    this.access_token = '';
    localStorage.clear();
    localStorage.removeItem("Email");
  }
  constructor(private http: Http, private socket:SocketService,
    private snackBar:MatSnackBar) { }
  private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json',
  'Access-Control-Allow-Origin' : 'http://localhost:4200', 'Access-Control-Allow-Credentials': 'true'});
  
  isLoggedIn(){
    // return true;
    // console.log("Logged in status: ", !(localStorage.getItem('access_token') == null));
    return !(localStorage.getItem('access_token') == null);
  }

  signup(firstName, lastName, email, userName, password){
    let json = JSON.stringify({
      firstName:firstName,
      lastName:lastName,
      email:email,
      userName:userName,
      password:password
    });
    console.log(json);
    return this.http.post('http://172.23.238.176:8088/v0.1/userinfo/user', json, {headers: this.headers}).toPromise().then((response)=> console.log(response)) ;
  }

  login(username,password){
    let json = 'grant_type=password&password=akshay&username=akshay';
    let body = new URLSearchParams();
    body.set('username','akshay');
    body.set('password','akshay');
    body.set('grant_type','password');
    let headers = new Headers();
    //headers.append('Content-Type', 'application/X-www-form-urlencoded');
    headers.append('Authorization','Basic Zm9vQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=');
    console.log("Logging in using the credentials..",username,password);
    return this.http.post('http://172.23.238.176:8087/oauth/token?grant_type=password&username=' + username + '&password=' + password, json, {headers: headers}).toPromise().then((response)=> {
      // response = response.json();
      localStorage.setItem('access_token',response.json().access_token);
      localStorage.setItem('refresh_token',response.json().refresh_token);
      localStorage.setItem('Email', username);
      console.log('USER',localStorage.getItem('Email'));
      this.socket.subscribe();
      console.log("Access token from service",localStorage.getItem('access_token'), localStorage.getItem('refresh_token'));
    }).catch(() => {
      let config = new MatSnackBarConfig();
      config.duration = 1000;
      this.snackBar.open("Wrong username/password.", '', config) ;
    }) ;
  }
}
