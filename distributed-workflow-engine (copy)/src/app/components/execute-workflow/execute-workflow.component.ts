import { SocketService } from "./../../services/socket/socket.service";
import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Router } from "@angular/router";
import { MatSnackBarConfig } from "@angular/material";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { ReportService } from "../../services/report/report.service";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

@Component({
  selector: "app-execute-workflow",
  templateUrl: "./execute-workflow.component.html",
  styleUrls: ["./execute-workflow.component.css"]
})
export class ExecuteWorkflowComponent implements OnInit {
  constructor(
    private authentication: AuthenticationService,
    private router: Router,
    private socketService: SocketService,
    private reportService: ReportService
  ) {}

  onSelect(event) {
    console.log(event);
  }

  response: any;

  //Charts options
  viewCharts: boolean;
  color = "primary";
  displayWaterfall = false;
  viewWaterfall = false;
  tasks : any;
  single: any[];
  multi: any[];
  view: any[] = [1000, 150];
  showXAxis = false;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = "Tasks";
  yAxisLabel = "Timeline";
  colorScheme = {
    domain: ["#fad73f", "#663ab7", "#fad73f", "#AAAAAA"]
  };

  //Other variables
  isLinear = true;
  value = 0;
  tasksComplete = 0;
  consoleOutput: Array<String>;
  taskList: Array<String>;
  displayStepper = false;
  cardDisplay = false;
  notLoaded = false;

  ngOnInit() {
    this.value = 0;
    this.tasksComplete = 0;
    this.consoleOutput = [];
    this.taskList = [];
    this.tasks = [];
    this.displayStepper = false;
    this.cardDisplay = false;
    this.viewCharts = false;

    this.socketService.socketMessages.subscribe(data => {
      this.tasksComplete += 1;
      console.log(
        "Completed" +
          this.tasksComplete +
          " of " +
          this.socketService.socketNumber
      );
      console.log(data.indexOf("failed"));
      if (data.indexOf("failed") !== -1) this.color = "warn";
      else
        this.value = this.tasksComplete / this.socketService.socketNumber * 100;
      console.log(this.value);
      if (this.value == 100) this.displayWaterfall = true;
    });

    this.socketService.socketNames.subscribe(data => {
      this.taskList.push(data);
      console.log("LENGTH", this.taskList.length);
      if (this.taskList.length == this.socketService.socketNumber)
        this.displayStepper = true;
    });
  }

  loadConsole(taskName) {
    this.cardDisplay = !this.cardDisplay;
    if (!this.cardDisplay) this.consoleOutput = [];
    try {
      let taskOutput = this.socketService.socketConsoleMap.get(taskName);
      this.notLoaded = false;
      for (var i = 0; i <= taskOutput.length; i++)
        this.consoleOutput.push(taskOutput[i]);
      // console.log("Console output: ", this.consoleOutput);
    } catch (e) {
      this.notLoaded = true;
    }
  }

  viewWaterFall() {
    this.viewCharts = true;
    this.reportService
      .getReport(this.socketService.jobId)
      .toPromise()
      .then(res => {
        this.loadChart(res.json() as any[]);
      });
  }

  loadChart(res) {
    console.log("RES", res);
    this.tasks = [];
    for (var i = 0; i < res.length; i++) {
      console.log(
        "not running: ", res[i]["taskAlias"], " ",  
        res[i]["taskStartTime"] - res[i]["jobStartTime"]
      );
      console.log("runtime", res[i]["taskStartTime"] - res[i]["taskEndTime"]);
      let taskWater = {
        name: res[i]["taskAlias"],
        series: [
          {
            name: "Not started",
            value: (Math.abs((res[i]["taskStartTime"] - res[i]["jobStartTime"]) as number))%res[i]["jobStartTime"]
          },
          {
            name: "Runtime",
            value: (Math.abs((res[i]["taskStartTime"] - res[i]["taskEndTime"]) as number))%res[i]["jobStartTime"]
          },
          {
            name: "Idletime",
            value: (Math.abs((res[i]["taskEndTime"] - res[i]["jobEndTime"]) as number))%res[i]["jobStartTime"]
          }
        ]
      };
      console.log(taskWater);
      this.tasks.push(taskWater);
      // console.log("Iteration ", i, " ", this.tasks);
    }
    console.log(this.tasks);
  }

  ngOnDestroy() {
    this.value = 0;
    this.tasksComplete = 0;
    this.consoleOutput = null;
    this.taskList = [];
    this.tasks = []
    this.displayStepper = false;
    this.cardDisplay = false;
  }
}
