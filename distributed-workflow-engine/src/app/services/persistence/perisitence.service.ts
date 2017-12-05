import { SocketService } from './../socket/socket.service';
import { Http, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { Task } from "../../model/task";
import { WorkFlow } from "../../model/workflow";
import { AppConfig } from "../../app.config";
import { UUID } from "angular2-uuid";
@Injectable()
export class PerisitenceService {
  constructor(private http: Http, private config: AppConfig,
  private socketService: SocketService) {}
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  triggerEngine(workFlowName) {
    this.socketService.workFlowName = workFlowName;
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    console.log("Triggering...");
    let jobId = UUID.UUID();
    localStorage.setItem("jobId",jobId);
    localStorage.setItem("workFlowName",workFlowName);
    this.socketService.subscribe(jobId);
    console.log(this.config.triggerEngine +
      localStorage.getItem("Email") +
      "/" +
      localStorage.getItem("jobId") +
      "/" +
      workFlowName);
    return this.http
      .get(
        this.config.triggerEngine +
          localStorage.getItem("Email") +
          "/" +
          localStorage.getItem("jobId") +
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

  sendWorkFlow2(workflowName, owner, description, status, tasks) {
    let workflow = new WorkFlow(workflowName, owner, description, [], [], status, tasks);
    console.log(JSON.stringify(workflow));
    return this.http
      .post(this.config.saveWorkflow, JSON.stringify(workflow), {
        headers: this.headers
      })
      .toPromise();
  }

  updateWorkFlow(oldworkflowName,workflowName, owner, description, status, tasks) {
    let workflow = new WorkFlow(workflowName, owner, description,[], [], status, tasks);
    console.log(oldworkflowName);
    return this.deleteWorkFlow(oldworkflowName).then( () => {
        this.sendWorkFlow2(workflowName, owner, description, status, tasks);
      });
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

  getWorkflowNames(username: String) {
    return this.http.get(
      this.config.persistence + "users/" + localStorage.getItem("Email"),
      { headers: this.headers }
    );
  }
}
