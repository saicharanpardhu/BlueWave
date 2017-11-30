import { element } from "protractor";
import { Router } from "@angular/router";
import { SocketService } from "./../../services/socket/socket.service";
import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Component, OnInit, ViewChild, AfterViewInit } from "@angular/core";
import { ReportService } from "./../../services/report/report.service";
import { Http } from "@angular/http";
import { PageEvent } from "@angular/material"; 
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
//import {PageEvent} from '@angular/material';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';

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
  // task: Task = []; 
  taskWaterfall : any;
  taskLogs: any;
  jobEndTime: any;
  jobStartTime: any;
  jobStatus: any;
  task : any;
  errorMsg:string;
  statusMsg:string;
  viewCharts: boolean;
  statuscode:any;
  color = "primary";
  displayWaterfall = false;
  viewWaterfall = false; 
  single: any[];
  multi: any[];
  view: any[] = [900, 150];
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = false;
  showXAxisLabel = true;
  tasks : any;
  xAxisLabel = "Tasks";
  yAxisLabel = "Timeline";
  colorScheme = {
    domain: ["#fad73f", "#663ab7", "#fad73f", "#AAAAAA"]
  };
  showWaterFall = false;

  //paginator variables 
  length ;
  pageSize;
  pageSizeOptions = [5, 10, 25, 100];
  pageEvent: PageEvent;
  pageIndex ;

  //console
  // cardDisplay = false
  constructor(
    private _service: ReportService,
    private workflowService: WorkflowDetailsService,
    private snackBar:MatSnackBar
  ) {}
  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }
  ngOnInit() {
    console.log("ngonit reports component ");
    this._service.getTaskNumber().then( res => this.length = res.json()); 
    this.pageSize = 10;
    this.pageIndex = 0;
    console.log(this.length);
    this.getAllJobReports();
  } 

  getAllJobReports(){
    console.log('username is',localStorage.getItem("Email"));
    this._service
    .getJobID(localStorage.getItem("Email"),this.pageIndex,this.pageSize)
    .subscribe(resData1 => {
      this.jobIdnames = resData1;
      console.log("jobidnames", this.jobIdnames); 
      this.jobId = this.jobIdnames[0].jobId; 
    }); 
  // console.log("USER", localStorage.getItem("Email")); 
  }

  getJobReports(jobId: String) {
    this.reports1 = [];
    this._service.getReport(jobId).subscribe(response => {
      this.jobStartTime = new Date(response.json()[0]["jobStartTime"]);
      this.jobEndTime = new Date(response.json()[0]["jobEndTime"]);
      this.jobStatus = response.json()[0]["jobStatus"];
      // console.log(response.json().length);
      let len = response.json().length;
      this.tasks = [];
      this.taskLogs = [];
      for (let i = 0; i < len; i++) {
        this.task = [];
        this.task.taskAlias = response.json()[i]["taskAlias"];
        this.task.taskStartTime = new Date(response.json()[i]["taskStartTime"]);
        this.task.taskEndTime = new Date(response.json()[i]["taskEndTime"]);
        this.task.taskLogs = response.json()[i]["taskLogs"];
        this.taskWaterfall = {
          name: response.json()[i]["taskAlias"],
          series: [
            {
              name: "Not started",
              value: (Math.abs((response.json()[i]["taskStartTime"] - response.json()[i]["jobStartTime"]) as number))%response.json()[0]["jobStartTime"]
            },
            {
              name: "Runtime",
              value: (Math.abs((response.json()[i]["taskStartTime"] - response.json()[i]["taskEndTime"]) as number))%response.json()[0]["jobStartTime"]
            },
            {
              name: "Idletime",
              value: (Math.abs((response.json()[i]["taskEndTime"] - response.json()[i]["jobEndTime"]) as number))%response.json()[i]["jobStartTime"]
            }
          ]
        };
        this.tasks.push(this.taskWaterfall);
        this.taskLogs.push(this.task);
        // console.log(this.tasks);
      }
      this.showWaterFall = true;
    },
    resEmployeeError => {this.errorMsg = resEmployeeError;
      //this.snackBar.open(resEmployeeError,'close');
      this.statusMsg = 'Error, Please try after sometime';
      this.snackBar.open('Cannot fetch Data, Please check Backend:'+ resEmployeeError,'close');
    }
  );
  } 

  updatePage(){
    this.pageIndex = this.pageEvent.pageIndex;
    this.pageSize = this.pageEvent.pageSize;
    this.getAllJobReports();
  }
}
 