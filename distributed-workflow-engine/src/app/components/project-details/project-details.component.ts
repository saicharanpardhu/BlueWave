import { Component, OnInit, Input, Output } from '@angular/core';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  public datas : any;

    constructor(private workflow_service : WorkflowDetailsService,
    private router:Router) {
    }

    ngOnInit(){
      this.load_workflows();

    }

    load_workflows() {
      return this.workflow_service.getWorkflow().subscribe(datas => {
        this.datas = datas ;
      });
    }


    getTask(workFlowName : String) {
      this.workflow_service.getTasksOfWorkflow(workFlowName).then(()=>
      this.router.navigate(['/workflow/workflowdetails'])
    );
  }

}
