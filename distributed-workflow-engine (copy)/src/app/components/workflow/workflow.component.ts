import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Router } from "@angular/router";
import { Input, Output } from "@angular/core";
import { WorkflowDetailsService } from "./../../services/workflow-details/workflow-details.service";
@Component({
  selector: "app-workflow",
  templateUrl: "./workflow.component.html",
  styleUrls: ["./workflow.component.css"]
})
export class WorkflowComponent implements OnInit {
  public datas: any;
  statuscode: any;

  constructor(
    private workflow_service: WorkflowDetailsService,
    private router: Router,
    private persistence: PerisitenceService
  ) {}

  ngOnInit() {
    this.load_workflows();
  }

  load_workflows() {
    return this.workflow_service.getAllWorkflows().then(datas => {
      this.datas = datas;
      console.log(this.datas);
    }).catch((err) => {
      // Handle any error that occurred in any of the previous
      console.error('I am the error in workflow',err);
      console.error(err.status);
      this.statuscode = err.status;
    });
  }
  getCurrentWorkflow(workFlowName: String) {
    this.workflow_service.currentWorkflowName = workFlowName;
  }

  getTask(workFlowName: String) {
    this.workflow_service
      .getTasksOfWorkflow(workFlowName)
      .then(() => this.router.navigate(["/workflows/workflowdetails"]));
  }
  getWorkflow(workFlowName) {
    this.workflow_service.getWorkflow(workFlowName).then(() => {
      this.router.navigate(["/viewworkflow"]);
    });
  }

  executeWorkFlow(workFlowName) {
    this.persistence.triggerEngine(workFlowName);
  }

  deleteWorkFlow(workFlowName) {
    this.persistence.deleteWorkFlow(workFlowName).then(() => {
      console.log("Loading new workflows..");
      this.load_workflows();
    });
  }
  viewmodeexit(): void {
    this.workflow_service.displayWorkflow = null;
  }
}
