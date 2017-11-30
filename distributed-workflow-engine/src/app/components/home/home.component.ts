import { multi } from "./data";
import { ReportService } from "./../../services/report/report.service";
import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { Http } from "@angular/http";
import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Router } from "@angular/router";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';
@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
  statuscode:any;
  constructor(
    private authentication: AuthenticationService,
    private workflowService: WorkflowDetailsService,
    private snackBar:MatSnackBar
  ) {
    Object.assign(this, { multi });
  }

  single: any[];
  multi: any[];
  public workflows: any;

  workflowList: any[];
  taskWater :any;
  view: any[] = [1000, 250];
  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  // showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Workflow';
  showYAxisLabel = true;
  yAxisLabel = 'Time';
  colorScheme = { 
    domain: [ '#ffffff', '#5590BF']    
  }; 
  ngOnInit() {    
  console.log(navigator.onLine); 
  console.log("****************************************************************");
  this.load_workflows();
  console.log("****************************************************************");

}

load_workflows() {
  return this.workflowService.getAllWorkflows().then(workflows => {
    this.workflows = workflows;
    console.log("W");
    console.log(this.workflows);
    this.loadHeatmap(workflows as any[]);
  }).catch((err) => {
    // Handle any error that occurred in any of the previous
    console.error('I am the error of auth3',err);
    console.error(err.status);
    this.statuscode = err.status;
    this.snackBar.open("Network Connection Error. Please try after sometime.",'close');
  })
  ;
}

showHeatMap = false;
loadHeatmap(res){
  console.log("RES",res); 
  this.workflowList = [];
  console.log(res.length);
  for( var i = 0; i <res.length ; i ++){ 
    console.log(res[i]["workFlowName"]);
    this.taskWater = {
      "name": res[i]["workFlowName"],
      "series": [
        {
          "name":"Jan",
          "value": 0
        },
        {
          "name":"Feb",
          "value": 0
        },
        {
          "name":"Mar",
          "value": 0
        },
        {
          "name":"Apr",
          "value": 0
        },
        {
          "name":"May",
          "value": 0
        },
        {
          "name":"Jun",
          "value": 0
        },
        {
          "name":"Jul",
          "value": 0
        },
        {
          "name":"Aug",
          "value": 0
        },
        {
          "name":"Sep",
          "value": 0
        },
        {
          "name":"Oct",
          "value": 0
        },
        {
          "name":"Nov",
          "value": 0
        },
        {
          "name":"Dec",
          "value": 0
        }
      ]
    };
    
    var Map = { 1: "Jan", 2: "Feb", 3: "Mar", 4:"Apr", 5:"May", 6: "Jun", 
    7: "Jul", 8: "Aug", 9: "Sep", 10: "Oct", 11: "Nov", 12:"Dec"}

    if(res[i]["executionTime"] != null){
      for(var j=0; j<res[i]["executionTime"].length; j++)
      {
        console.log("Task execution time: " , res[i]["executionTime"][j]);
        let mod = new Date(res[i]["executionTime"][j]).getMonth()+1;
        console.log("Task execution month: ", mod);
        console.log("Task execution date: ", new Date(res[i]["executionTime"][j]));
        for(let task of this.taskWater.series){ 
          if(task.name == Map[mod]){
            task.value++;
            console.log(mod,task.value);
          }
        }
        console.log(res[i]["executionTime"][j]);


      }
      console.log("?");
      
    }

    this.workflowList.push(this.taskWater);
  
  }

  console.log(this.workflowList);
  this.showHeatMap = true;
    

}
 

  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }

  onSelect(event) {
    console.log(event);
  }
}
