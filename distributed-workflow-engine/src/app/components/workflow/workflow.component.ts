import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { Input, Output } from '@angular/core';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';
@Component({
  selector: 'app-workflow',
  templateUrl: './workflow.component.html',
  styleUrls: ['./workflow.component.css']
})
export class WorkflowComponent implements OnInit {
 
  
  public datas : any;
  
      constructor(private workflow_service : WorkflowDetailsService,
      private router:Router) {
      }
  
      ngOnInit(){
        this.load_workflows();
        console.log("uiawfchiwfc");
      }
  
      load_workflows() {
        return this.workflow_service.getAllWorkflows().subscribe(datas => {
          console.log(datas+"aerfeugrfeiufeiu");
          this.datas = datas ;
        });
      }
      getCurrentWorkflow(workFlowName : String) {
        this.workflow_service.currentWorkflowName = workFlowName;
      }
  
  
      getTask(workFlowName : String) {
        this.workflow_service.getTasksOfWorkflow(workFlowName).then(()=>
        this.router.navigate(['/workflows/workflowdetails'])
      );
      
    }
    getWorkflow(workFlowName){
      this.workflow_service.getWorkflow(workFlowName).then(()=> this.router.navigate(['/addworkflow']));
    }
}