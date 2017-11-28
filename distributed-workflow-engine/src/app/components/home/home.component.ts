import { multi } from "./data";
import { ReportService } from "./../../services/report/report.service";
import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { Http } from "@angular/http";
import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { Router } from "@angular/router";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";
@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
  constructor(
    private authentication: AuthenticationService,
    private workflowService: WorkflowDetailsService
  ) {
    Object.assign(this, { multi });
  }

  single: any[];
  multi: any[];
  view: any[] = [1000, 200];
  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  // showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = "Workflow";
  showYAxisLabel = true;
  yAxisLabel = "Time";
  colorScheme = {
    domain: ["#fafafa", "#663ab7"]
  };

  ngOnInit() {}

  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }

  onSelect(event) {
    console.log(event);
  }
}
