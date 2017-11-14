import { Router, RouterModule } from '@angular/router';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workflow-details',
  templateUrl: './workflow-details.component.html',
  styleUrls: ['./workflow-details.component.css'],

})
export class WorkflowDetailsComponent implements OnInit {

  show : boolean = false;
  panelOpenState: boolean = false;
  tasks : any;
  workFlowName : any;
  taskDetails : any;
  nameoftask: any;

  constructor(private workflowService : WorkflowDetailsService ) {
    // this.taskDetails = "a";
  }
  // taskDetail?

  ngOnInit() {
    this.loadWorkFlowDetails(this.tasks[i]);
    this.tasks = this.workflowService.tasks;
    this.workFlowName = this.workflowService.currentWorkflowName;
    console.log("Wfname "+this.workFlowName);
  }

  loadWorkFlowDetails(nameOfTask: String) {
    console.log("loading task details..", nameOfTask);
    // console.log(this.workflowService.getTaskDetailsOfWorkflow(this.workFlowName,nameOfTask));
    return this.workflowService.getTaskDetailsOfWorkflow(this.workFlowName,nameOfTask).then( res => {
      this.taskDetails  = res.json();
      console.log(this.taskDetails);
      this.show = true;
    });;


    // .then(datas => {
    //   console.log("fetched task details");
    //   // this.taskDetails = datas.json() ;
    //   console.log(this.taskDetails.status);
    // });


  }
}
