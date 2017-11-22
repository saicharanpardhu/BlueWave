import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http,Response } from '@angular/http';
import 'rxjs/add/operator/map'
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReportService {

  public displayReport;
  
    constructor(private _http:Http) { }

    getJobID(id){
      return this._http.get('http://172.23.238.147:8080/v1.0/persistence/jobdetails/userName/'+id).map((responseid:Response)=>responseid.json());
    
    }

    getReport(jobid: String){
      return this._http.get('http://172.23.238.176:8080/checkout/'+jobid).map((response:Response)=>response.json());
  }
//   getJobId(username: String){
//     return this._http.get('http://172.23.238.147:8080/v1.0/persistence/jobdetails/userName'+username).map((responsen:Response)=>responsen.json());
// }


  
  
}
