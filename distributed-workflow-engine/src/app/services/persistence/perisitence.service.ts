import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class PerisitenceService {
  constructor(private http: Http) { }
  
  triggerEngine(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    
    return this.http.post("http://172.23.238.216:8080/echo/workflow", JSON.stringify({
      jobId:"jobGit1",
      workFlowName:"CI-CD",
      payload: ["https://github.com/akshaydv/ReportingService.git"]
    }), {headers:headers}).toPromise().then((response) => response.json());
  }
}
