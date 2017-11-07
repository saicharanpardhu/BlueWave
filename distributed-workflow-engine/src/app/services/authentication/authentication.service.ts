import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

@Injectable()
export class AuthenticationService {

  private access_token : String = '';
  private refresh_token : String = '';

  public getAccessToken(){
    return this.access_token;
  }
  public setAccessToken(){
    this.access_token = '';
  }
  constructor(private http: Http) { }
  private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json',
  'Access-Control-Allow-Origin' : 'http://localhost:4200', 'Access-Control-Allow-Credentials': 'true'});
  
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
    
    return this.http.post('http://172.23.238.176:8087/oauth/token?grant_type=password&username=' + username + '&password=' + password, json, {headers: headers}).toPromise().then((response)=> {
      // response = response.json();
      this.access_token = response.json().access_token;
      this.refresh_token = response.json().refresh_token; 
      console.log("Access token from service",this.access_token, this.refresh_token);
    }) ;
  }
}
