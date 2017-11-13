import { Router, RouterModule } from '@angular/router';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workflow-details',
  templateUrl: './workflow-details.component.html',
  styleUrls: ['./workflow-details.component.css'],

})
export class WorkflowDetailsComponent implements OnInit {
<<<<<<< HEAD

  panelOpenState: boolean = false;
=======
  
>>>>>>> ed98ce2d92897bf9d7c949cbea54d9cf1de7a5b0
  tasks : any;

  constructor(private workflowService : WorkflowDetailsService ) {
  }

  ngOnInit() {
    this.tasks = this.workflowService.tasks;
  }


}
