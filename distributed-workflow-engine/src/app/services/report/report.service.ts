import { Injectable } from '@angular/core'; 
import { Http,Response,Headers } from '@angular/http';
import 'rxjs/add/operator/map'
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReportService {

  public displayReport;
  
    constructor(private _http:Http) { }
    private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json',
    'Access-Control-Allow-Origin' : 'http://localhost:4200', 'Access-Control-Allow-Credentials': 'true'});

    getJobID(id){ 
      return this._http.get('http://172.23.238.147:8080/v1.0/persistence/jobdetails/userName/'+id, {headers:this.headers} ).map((responseid:Response)=>responseid.json() as any[]);
    
    }

    getReport(jobid: String){
      return this._http.get('http://172.23.238.176:8080/checkout/'+jobid, {headers:this.headers}).toPromise().then(response => response.json() as any[]);
  }  
  
}
