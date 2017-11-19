import { Task } from './../create-workflow//models/task';
import { Router, RouterModule } from '@angular/router';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workflow-details',
  templateUrl: './workflow-details.component.html',
  styleUrls: ['./workflow-details.component.css'],

})
export class WorkflowDetailsComponent implements OnInit {
 
  panelOpenState: boolean = false;
  tasks : Array<any> = [];
  workFlowName : any; 
  showTasks = false;
  constructor(private workflowService : WorkflowDetailsService ) { } 

  ngOnInit() { 
    // for(var i = 0; i < this.workflowService.tasks.length; i++){
    //   this.tasks.push(this.loadWorkFlowDetails(this.workflowService.tasks[i]));
    //   console.log(this.tasks);
    // }
    this.workFlowName = this.workflowService.currentWorkflowName;  
    this.loadAllTasks();
  }

  loadAllTasks(){
    for(var i = 0; i < this.workflowService.tasks.length; i++){ 
      this.tasks.push(this.loadWorkFlowDetails(this.workflowService.tasks[i]));
      console.log(this.tasks);
    }
    this.showTasks = true;
  }

  loadWorkFlowDetails(nameOfTask: String) { 
    return this.workflowService.getTaskDetailsOfWorkflow(this.workFlowName,nameOfTask).then( res => res.json());; 


  }
}
