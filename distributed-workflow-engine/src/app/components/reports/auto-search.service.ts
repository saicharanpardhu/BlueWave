import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http,Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';

@Injectable()
export class WorkflowService{


constructor(private _http:Http){}
getWorkflow(id){
    return this._http.get('http://172.23.238.151:8080/v1.0/persistence/workflow/users/'+id).map((response:Response)=>response.json());
}

}
