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
    console.log("http://172.23.238.186:8082/v1.0/workflowname/"+localStorage.getItem('Email')+"/"+workFlowName);
    return this.http.get("http://172.23.238.186:8082/v1.0/workflowname/"+localStorage.getItem('Email')+"/"+workFlowName, {headers:headers}).toPromise().then((response) => response.json());
  }
  strMapToObj(strMap) {
    let obj = Object.create(null);
    for (let [k,v] of strMap) { 
        obj[k] = v;
        console.log(obj);
    }
    return obj;
}
  sendWorkFlow(){ 
    let clone = new Task("clone", "ready", null, [""], ["https://github.com/avalanche557/Spring_restapi.git"]);
    const map = Object.create(null);
    // {
    //   'clone':clone
    // };
    // const obj = map.reduce((o, [key, value]) => (o[key] = value, o), {});
    map['clone']=clone;
     console.log(JSON.stringify(map));
    let workflow = new WorkFlow("Maven","Akshay", ["Vaibhav"], ["Harsh"], "ready", map);
    console.log("hey"+JSON.stringify(workflow));
    // this.http.post("http://172.23.238.147:8099/v1.0/persistence/workflow", JSON.stringify(workflow), {headers:this.headers}).toPromise().then(response => console.log(response.json()));
  }
  //triggered when save clicked in UI for a workflow
  sendWorkFlow2(workflowName, owner, status, tasks){
    // console.log("w "+workflowName+" O "+owner+" status "+status);
    // console.log(JSON.stringify(tasks));
    // tasks = JSON.stringify(tasks);
    console.log("From service: " , workflowName);
    let workflow = new WorkFlow(workflowName,owner, ["Vaibhav"], ["Harsh"], status, tasks);
    return this.http.post("172.23.238.158:8080/v1.0/persistence/workflow/"+localStorage.getItem('Email'),
     JSON.stringify(workflow), {headers:this.headers}).toPromise();
  }

  updateWorkFlow(workflowName, owner, status, tasks){
    // console.log("w "+workflowName+" O "+owner+" status "+status);
    // console.log(JSON.stringify(tasks));
    // tasks = JSON.stringify(tasks);
    console.log("From service: " , workflowName);
    let workflow = new WorkFlow(workflowName,owner, ["Vaibhav"], ["Harsh"], status, tasks);
    return this.http.put("172.23.238.158:8080/v1.0/persistence/workflow/"+localStorage.getItem('Email'),
     JSON.stringify(workflow), {headers:this.headers}).toPromise();
  }

  deleteWorkFlow(workFlowName){
    return this.http.delete("172.23.238.158:8080/v1.0/persistence/workflow/"+localStorage.getItem('Email')).toPromise();
  }
}