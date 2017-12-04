import { Headers, Http } from '@angular/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '../../app.config';

@Injectable()
export class ProfileService {

  constructor(private http: Http,
  private config: AppConfig) { }
    
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  getUserDetails(username){
    console.log("Getting user details : ", username);
    // console.log("Url: ", this.config.userSignup + "/userName/" + username);
    return this.http.get(this.config.userSignup + "/userName/" + username).toPromise().then( res => {
      // console.log("From service: ", res);
      return res;
    });
  }

  update(user){
    return this.http.put(this.config.userSignup + "/updateuser", user, { headers: this.headers }).toPromise();
  }
}
