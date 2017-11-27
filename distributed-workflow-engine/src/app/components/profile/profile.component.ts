import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  constructor(
    private authentication: AuthenticationService,
    private router: Router,
    private workflowService: WorkflowDetailsService
  ) {}

  ngOnInit() {}
  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }
}
