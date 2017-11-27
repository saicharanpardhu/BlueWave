import { multi } from './data';
import { ReportService } from './../../services/report/report.service';
import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { Http } from '@angular/http';  
// import {NgxChartsModule} from '@swimlane/ngx-charts';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router'; 
import { Observable } from 'rxjs/Rx';
import { WorkflowDetailsService } from '../../services/workflow-details/workflow-details.service';
// import { isOnline } from 'is-online';
import { Subject } from "rxjs/Subject";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private isOnline : boolean;
  constructor(private authentication : AuthenticationService,
    private router: Router, private persistence: PerisitenceService,
    private reportService:ReportService, private workflowService: WorkflowDetailsService) { 
      Object.assign(this, {multi});
    }

    single: any[];
    multi: any[];
    view: any[] = [1000, 200];
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
    navigator$ = new Subject<boolean>();
    navigatorObs = this.navigator$.asObservable();
  ngOnInit() {   
    this.onlineCheck();
    this.navigatorObs.subscribe(data => this.isOnline = data);
    console.log(navigator.onLine); 
  }
  viewmodeexit():void{
    this.workflowService.displayWorkflow=null; 
  }  
  onSelect(event) {
    console.log(event);
  }
  private baseUrl = 'www.google.com';  
  
  onlineCheck() {
    let xhr = new XMLHttpRequest();
    return new Promise((resolve, reject)=>{
        xhr.onload = () => {
            // Set online status 
            this.navigator$.next(true);
            resolve(true);
        };
        xhr.onerror = () => {
            // Set online status 
            this.navigator$.next(false);
            reject(false);
        };
        xhr.open('GET', this.baseUrl, true);
        xhr.send();
      });  
    } 
}
 