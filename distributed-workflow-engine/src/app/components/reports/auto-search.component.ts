import { WorkflowService } from './auto-search.service';
import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import 'rxjs/add/operator/switchMap';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-auto-search',
  templateUrl: './auto-search.component.html',
  styleUrls: ['./auto-search.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AutoSearchComponent implements OnInit {
  stateCtrl: FormControl;
  filteredStates:any;

  
  states: any[];
  
  constructor(private _service:WorkflowService) {
    this.stateCtrl = new FormControl();
    
  }

  filterStates(name1: string) {
    console.log('inside filter states');
    let c= this.states.filter((state) =>
      { console.log('inside filterStates',state);
        console.log( state.workFlowName.toLowerCase().indexOf(name1.toLowerCase()) == 0)
          return state.workFlowName.toLowerCase().indexOf(name1.toLowerCase()) == 0;
      })
      console.log('filtre state return',c);
     return c;
  }  
  ngOnInit(){
    
   
     console.log('states got here');
   

    this._service.getWorkflow(localStorage.getItem("Email"))
    .subscribe(resData => {this.states = resData;
      console.log('states',this.states);
      this.fun();
      })
      

  }
    fun(){
       this.stateCtrl.valueChanges
      .startWith(null)
      .map(state => state ? this.filterStates(state) : this.states.slice())
      .subscribe((res)=>{
        console.log('inside subscribe',res);
        this.filteredStates =res
      })
      
    }
    
  }


