import { ReportService } from './../../services/report/report.service';
import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { Http } from '@angular/http';  
import {NgxChartsModule} from '@swimlane/ngx-charts';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router'; 
import { Observable } from 'rxjs/Rx';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authentication : AuthenticationService,
    private router: Router, private persistence: PerisitenceService,
    private reportService:ReportService) { 
     }

    single: any[];
    multi: any[];
    tasks : any[] = [];
    view: any[] = [500, 75];
  
    // options
    showXAxis = false;
    showYAxis = true;
    gradient = false;
    showLegend = false;
    showXAxisLabel = true;
    xAxisLabel = 'Timestamp';
    showYAxisLabel = true;
    yAxisLabel = 'Tasks';
  
    colorScheme = {
      domain: ['#fafafa', '#663ab7', '#C7B42C', '#AAAAAA']
    };
    ticks = 0;
  ngOnInit() {  
    /*this.reportService.getReport("28e84ec8-a46f-4c0d-9ae0-98126f6bf211").then( res => {
      this.loadChart(res);
    });*/
    let timer = Observable.timer(1,1);
    timer.subscribe(
      t => {
          this.ticks = t;
          // console.log(this.ticks);
    });
  console.log(this.tasks);
  }

  loadChart(res){
    console.log("RES",res);
    // if(res[])
    for( var i = 0; i <res.length ; i ++){
      console.log(res[i]["taskStartTime"]);
      console.log(res[i]["jobStartTime"]);
      console.log(res[i]["taskEndTime"]);
      let task = {
        "name" : res[i]["taskAlias"],
        "series": [ ]
      }
      if(res[i]["taskStartTime"]){
        task.series[0] = {
          "name":"not started",
          "value" : res[i]["taskStartTime"] - res[i]["jobStartTime"]
        };
      }else{
        task.series[0] = {
          "name":"not started",
          "value" : this.ticks - res[i]["jobStartTime"]
        }
      }

      // {
      //   "name" : "not started",
      //   "value" : res[i]["taskStartTime"] - res[i]["jobStartTime"]
      // },{
      //   "name" : "runtime",
      //   "value" : res[i]["taskEndTime"] - res[i]["taskStartTime"]
      // }
      this.tasks.push(task);
      console.log("Iteration ",i, " ", this.tasks);
    }
  }
  sendWorkFlow(){
    this.persistence.sendWorkFlow();
  }
  executeWorkflow(){ 
    this.persistence.triggerEngine("git-process2");
  }
}
 