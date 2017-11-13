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
  tasks : any;

  constructor(private workflowService : WorkflowDetailsService ) {
  }

  ngOnInit() {
    this.tasks = this.workflowService.tasks;
  }


}
