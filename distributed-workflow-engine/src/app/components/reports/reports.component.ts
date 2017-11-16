import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ReportService } from './../../services/report/report.service';
import {HttpClient} from '@angular/common/http';
import {MatPaginator, MatSort} from '@angular/material';
import {MatTableModule} from '@angular/material'; 
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
export class ReportsComponent   {

}