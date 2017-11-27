import { async, ComponentFixture, TestBed } from "@angular/core/testing";
// import { CreateWorkflowComponent, DialogOverviewDialog, WnameOverviewDialog, SettingsDialog, JsonEditor } from '../../create-workflow.component';
import { MaterialModule } from "../../modules/material.module";
import { AppRoutingModule } from "../../modules/app-routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { ExecuteWorkflowComponent } from "../execute-workflow/execute-workflow.component";
import { GetReportComponent } from "../reports/reports.component";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { NgxChartsModule } from "@swimlane/ngx-charts"; 
import { SocketService } from "../../services/socket/socket.service";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";
import { PerisitenceService } from "../../services/persistence/perisitence.service";
import { APP_BASE_HREF } from "@angular/common";
import { StompService } from "ng2-stomp-service";
import { ReportService } from "../../services/report/report.service";
import { AuthGuardService } from "../../services/authentication/auth-guard.service";
import { AuthenticationService } from "../../services/authentication/authentication.service";

import { AppConfig } from "../../app.config";

describe("ExecuteWorkflowComponent", () => {
  let component: ExecuteWorkflowComponent;
  let fixture: ComponentFixture<ExecuteWorkflowComponent>;
  class SocketServiceStub extends SocketService{
    
  }
  beforeEach(
    async(() => {
      TestBed.configureTestingModule({
        declarations: [  
          ExecuteWorkflowComponent, 
          GetReportComponent
        ],
        imports: [
          BrowserModule,
          AppRoutingModule,
          MaterialModule, 
          HttpModule,
          NgxChartsModule
        ],
        providers: [
          {provide: SocketService, useValue: SocketServiceStub },
          AppConfig, 
          AuthGuardService,  
          ReportService,
          { provide: APP_BASE_HREF, useValue: "/" }
        ]
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(ExecuteWorkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
