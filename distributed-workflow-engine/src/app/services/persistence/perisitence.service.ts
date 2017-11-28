import { Http, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { Task } from "../../model/task";
import { WorkFlow } from "../../model/workflow";
import { AppConfig } from "../../app.config";
@Injectable()
export class PerisitenceService {
  constructor(private http: Http, private config: AppConfig) {}
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  triggerEngine(workFlowName) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    return this.http
      .get(
        this.config.triggerEngine +
          localStorage.getItem("Email") +
          "/" +
          workFlowName,
        { headers: headers }
      )
      .toPromise()
      .then(response => response.json());
  }
  strMapToObj(strMap) {
    let obj = Object.create(null);
    for (let [k, v] of strMap) {
      obj[k] = v;
      console.log(obj);
    }
    return obj;
  }

  sendWorkFlow2(workflowName, owner, status, tasks) {
    let workflow = new WorkFlow(workflowName, owner, [], [], status, tasks);
    return this.http
      .post(this.config.saveWorkflow, JSON.stringify(workflow), {
        headers: this.headers
      })
      .toPromise();
  }

  updateWorkFlow(workflowName, owner, status, tasks) {
    let workflow = new WorkFlow(workflowName, owner, [], [], status, tasks);
    return this.http
      .put(this.config.saveWorkflow, JSON.stringify(workflow), {
        headers: this.headers
      })
      .toPromise();
  }

  deleteWorkFlow(workFlowName) {
    return this.http
      .delete(
        this.config.removeWorkflow +
          localStorage.getItem("Email") +
          "/" +
          workFlowName
      )
      .toPromise();
  }

  getWorkflowNames(username:String){
    
    return this.http.get(this.config.persistence + "users/"+localStorage.getItem('Email'), {headers:this.headers});    

  }


}
