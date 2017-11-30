import { Injectable } from "@angular/core";
import { Http, Response, Headers } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from "rxjs/Observable";
import { AppConfig } from "../../app.config";
import { HttpInterceptor } from './../../services/interceptor.service';

@Injectable()
export class ReportService {
  public displayReport;

  constructor(private _http:  HttpInterceptor, private config: AppConfig) {}
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  getJobID(id, pageindex, pagesize) {
    console.log(this.config.reportGetJobId + id + "/" + pageindex + "/" + pagesize);
    return this._http
      .get(this.config.reportGetJobId + id+ "/" + pagesize + "/" + pageindex, { headers: this.headers })
      .map((responseid: Response) => responseid.json() as any[]);
  }

  getReport(jobid: String) {
    console.log("getreport service method", jobid);
    return this._http.get(this.config.getReport + jobid, {
      headers: this.headers
    });
  }

  getTaskNumber(){
    return this._http.get(this.config.getTaskNumber+localStorage.getItem('Email')).toPromise();
  }
}
