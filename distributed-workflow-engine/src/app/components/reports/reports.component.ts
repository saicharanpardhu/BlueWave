import { element } from 'protractor';
import { Router } from '@angular/router'; 
import { SocketService } from './../../services/socket/socket.service';
import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication/authentication.service';


import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ReportService } from './../../services/report/report.service';
import {Http} from '@angular/http';
import {MatPaginator, MatSort, PageEvent} from '@angular/material';
import {MatTableModule} from '@angular/material';
import {MatPaginatorModule} from '@angular/material/paginator';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/switchMap';
import { Timestamp } from 'rxjs';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class GetReportComponent implements OnInit {
  
    reports: any;
    jobIdnames: any;
    jobId: String;
    tasks:any;
    jobEndTime="sfsfgvsvgsvs";
    jobStartTime: any;
    jobStatus :any;
    constructor(private _service:ReportService) { }
  
    ngOnInit() {


      this._service.getJobID(localStorage.getItem('Email'))
      .subscribe(resData1 => {this.jobIdnames = resData1;
        console.log('jobidnames',this.jobIdnames);
        console.log('jobId',this.jobIdnames[0].jobId);
        console.log('WjobId',this.jobIdnames[0].workFlowName);
        this.jobId= this.jobIdnames[0].jobId;
        this.jobIdnames.forEach(element => {
          console.log('job id is',element.jobId);
          this.callLater(element.jobId);
        });
        
        })
        // this._service.getReport("5a0f90ba-dde4-49cc-ae11-0999227eec37")
        // .subscribe((resData) => {
        //   console.log('response data is',resData);
        //   this.reports = resData;
        //   //console.log('reports',this.reports);
        //   //console.log('jobId',this.reports[0].jobId);
        //   //console.log('WjobId',this.reports[0].workFlowName);
    
        //   })




      

        // this._service.getReport(this.jobId)
        // .subscribe(resData => {this.jobIdname = resData;
        //   console.log('jobIds',this.jobIdname);
        console.log('USER',localStorage.getItem('Email'));
          
    
        //   })
      
      
    }
    callLater(val) {
      console.log('val is in call leater',val);
      this._service.getReport(val)
      .subscribe((resData) => {
        console.log('response data is',resData);
        this.reports = resData;
        //console.log('reports',this.reports);
        //console.log('jobId',this.reports[0].jobId);
        //console.log('WjobId',this.reports[0].workFlowName);
  
        })
        return this.reports;
    }
    // loadTasks(jobId:String){
           
    //        this.callLater(jobId).subscribe(
    //         (resData) => {
    //           this.tasks = resData;
    //         }
    //        );
    //        if(this.tasks[0].jobEndTime!=undefined)
    //        this.jobEndTime =this.tasks[0].jobEndTime;
    //        if(this.tasks[0].jobStartTime!=undefined)
    //        this.jobStartTime = this.tasks[0].jobStartTime;
    //        if(this.tasks[0].jobStatus!=undefined)
    //        this.jobStatus = this.tasks[0].jobStatus;
    // }
  }
  
  @Component({
    selector: 'app-reports',
    templateUrl: './reports.component.html',
    styleUrls: ['./reports.component.css']
  })

export class ExpansionSteps {
  step = 0;

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }
}