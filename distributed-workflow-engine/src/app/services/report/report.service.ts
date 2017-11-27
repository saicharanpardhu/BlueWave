import { Injectable } from "@angular/core";
import { Http, Response, Headers } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from "rxjs/Observable";
import { AppConfig } from "../../app.config";

@Injectable()
export class ReportService {
  public displayReport;

  constructor(private _http: Http, private config: AppConfig) {}
  private headers = new Headers({
    "Content-Type": "application/json",
    Accept: "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
    "Access-Control-Allow-Credentials": "true"
  });

  getJobID(id) {
    return this._http
      .get(this.config.reportGetJobId + id, { headers: this.headers })
      .map((responseid: Response) => responseid.json() as any[]);
  }

  getReport(jobid: String) {
    console.log("getreport service method", jobid);
    return this._http.get(this.config.getReport + jobid, {
      headers: this.headers
    });
  }
}
