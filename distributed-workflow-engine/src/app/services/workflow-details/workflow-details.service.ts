import { Http,HttpModule,Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise'
@Injectable()
export class WorkflowDetailsService {
  // public workflow_obj : any;



  public datas : any;
  public tasks : any;
  constructor(private _http:Http){
    console.log("service running");
  }
  getWorkflow(){
      return this._http.get('http://localhost:8080/v1.0/persistence/workflow').map(res=>res.json());
  }

  getTasksOfWorkflow(workFlowName : String){
    return this._http.get("http://localhost:8080/v1.0/persistence/tasks/"+workFlowName).toPromise().then((res) => {
      this.tasks = res.json();
      console.log(this.tasks);
    });
   }
   getTasks(){
     return this.tasks;
   }
}