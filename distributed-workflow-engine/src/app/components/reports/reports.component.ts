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



export class ReportsComponent {
  displayedColumns = ['position', 'blob', 'level'];
 // dataSource = ELEMENT_DATA;
  

 // @ViewChild(MatPaginator) paginator: MatPaginator;

  /**
   * Set the paginator after the view init since this component will
   * be able to query its view for the initialized paginator.
   */
  ngAfterViewInit() {
    //this.dataSource.paginator = this.paginator;
    //console.log(this.dataSource);
  }
} 

export interface Element {
  blob: string;
  position: number;
  level: string;
}

const ELEMENT_DATA: Element[] = [
  {position: 1, blob: 'Message1', level: 'low'},
  {position: 2, blob: 'Message2', level: 'low'},
  {position: 3, blob: 'Message3', level: 'low'},
  {position: 4, blob: 'Message4', level: 'low'},
  {position: 5, blob: 'Message5', level: 'low'},
  {position: 6, blob: 'Message6', level: 'low'},
  {position: 7, blob: 'Message7', level: 'low'},
];

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