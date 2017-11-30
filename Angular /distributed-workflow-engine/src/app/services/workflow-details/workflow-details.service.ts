import { Http, Response } from "@angular/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import { AppConfig } from "../../app.config";
@Injectable()
export class WorkflowDetailsService {
  // public workflow_obj : any;
  public datas: any;
  public tasks: any;
  public taskDetails: any;
  public currentWorkflowName: any;
  public workflow;
  public displayWorkflow;
  constructor(private _http: Http, private config: AppConfig) {
    console.log("service running");
  }
  getAllWorkflows() {
    return this._http
      .get(this.config.getAllWorkflows + localStorage.getItem("Email"))
      .toPromise()
      .then(res => res.json());
  }

  saveTaskType(taskType, command) {
    let json = {
      shellScript:
        "#!/bin/bash\nchmod +x Clone-Plugin.sh\nsource config.sh\nmkdir $directory_path\ncd $directory_path\ngit clone $git_url",
      shellPath: "/home/prashant/Desktop/pmd/shell_script/Test",
      fileName: "testingthrouh.sh"
    };
    // return this._http.post();
  }
  getWorkflow(workFlowName) {
    return this._http
      .get(this.config.getWorkflow + localStorage["Email"] + "/" + workFlowName)
      .toPromise()
      .then(res => {
        this.displayWorkflow = res.json();
      });
  }
  getTasksOfWorkflow(workFlowName: String) {
    return this._http
      .get(this.config.getTasksOfWorkflow + workFlowName)
      .toPromise()
      .then(res => {
        this.tasks = res.json();
        console.log(this.tasks);
      });
  }
  getTaskDetailsOfWorkflow(workFlowName: String, taskName: String) {
    return this._http
      .get(
        "http://172.23.238.151:8080/v1.0/persistence/tasks/" +
          workFlowName +
          "/" +
          taskName
      )
      .toPromise();
  }
  getTasks() {
    return this.tasks;
  }
}
