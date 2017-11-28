import { element } from "protractor";
import { Router } from "@angular/router";
import { SocketService } from "./../../services/socket/socket.service";
import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Component, OnInit, ViewChild, AfterViewInit } from "@angular/core";
import { ReportService } from "./../../services/report/report.service";
import { Http } from "@angular/http";
import { MatPaginator, MatSort, PageEvent } from "@angular/material";
import { MatTableModule } from "@angular/material";
import { MatPaginatorModule } from "@angular/material/paginator";
import { DataSource } from "@angular/cdk/collections";
import { Observable } from "rxjs/Observable";
import "rxjs/add/observable/merge";
import "rxjs/add/observable/of";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/switchMap";
import { Timestamp } from "rxjs";
import { Task } from "./task";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";

@Component({
  selector: "app-reports",
  templateUrl: "./reports.component.html",
  styleUrls: ["./reports.component.css"]
})
export class GetReportComponent implements OnInit {
  reports: any;
  reports1: any;
  jobIdnames: any;
  jobId: String;
  task: Task = [];
  tasks: any[];
  tasks1: any;
  jobEndTime: any;
  jobStartTime: any;
  jobStatus: any;
  constructor(
    private _service: ReportService,
    private workflowService: WorkflowDetailsService
  ) {}
  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }
  ngOnInit() {
    console.log("ngonit reports component ");
    this._service
      .getJobID(localStorage.getItem("Email"))
      .subscribe(resData1 => {
        this.jobIdnames = resData1;
        console.log("jobidnames", this.jobIdnames);
        console.log("jobId", this.jobIdnames[0].jobId);
        console.log("WjobId", this.jobIdnames[0].workFlowName);
        this.jobId = this.jobIdnames[0].jobId; 
      }); 
    console.log("USER", localStorage.getItem("Email")); 
  } 

  getJobReports(jobId: String) {
    this.reports1 = [];
    this._service.getReport(jobId).subscribe(response => {
      this.jobStartTime = new Date(response.json()[0]["jobStartTime"]);
      this.jobEndTime = new Date(response.json()[0]["jobEndTime"]);
      this.jobStatus = response.json()[0]["jobStatus"];
      console.log(response.json().length);
      let len = response.json().length;
      this.tasks = [];

      for (let i = 0; i < len; i++) {
        this.task = [];
        this.task.taskAlias = response.json()[i]["taskAlias"];
        this.task.taskStartTime = new Date(response.json()[i]["taskStartTime"]);
        this.task.taskEndTime = new Date(response.json()[i]["taskEndTime"]);
        this.task.taskLogs = response.json()[i]["taskLogs"];
        this.tasks.push(this.task);
        console.log(this.tasks);
      }
    });
  } 
}
 