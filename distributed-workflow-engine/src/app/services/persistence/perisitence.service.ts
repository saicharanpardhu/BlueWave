import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Task } from '../../model/task';
import { WorkFlow } from '../../model/workflow';

@Injectable()
export class PerisitenceService {
  constructor(private http: Http) { }
  private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json',
  'Access-Control-Allow-Origin' : 'http://localhost:4200', 'Access-Control-Allow-Credentials': 'true'});
  triggerEngine(workFlowName){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    
    return this.http.get("http://172.23.238.186:8021/v1.0/workflowname/"+workFlowName, {headers:headers}).toPromise().then((response) => response.json());
  }

  sendWorkFlow(){ 
    let clone = new Task("clone", "ready", null, [""], ["https://github.com/avalanche557/Spring_restapi.git"]);
    let map = {
      'clone':clone
    };
    let workflow = new WorkFlow("Maven","Akshay", ["Vaibhav"], ["Harsh"], "ready", map);
    console.log(JSON.stringify(workflow));
    this.http.post("http://172.23.238.147:8080/v1.0/persistence/workflow", JSON.stringify(workflow), {headers:this.headers}).toPromise().then(response => console.log(response.json()));
  }
}
