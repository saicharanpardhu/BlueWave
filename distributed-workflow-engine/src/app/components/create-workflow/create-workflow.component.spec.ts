import { async, ComponentFixture, TestBed } from "@angular/core/testing";
import {
  CreateWorkflowComponent,
  DialogOverviewDialog,
  WnameOverviewDialog,
  SettingsDialog,
  JsonEditor
} from "./create-workflow.component";
import { MaterialModule } from "../../modules/material.module";
import { AppRoutingModule } from "../../modules/app-routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpModule, Http } from "@angular/http";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { NgxChartsDagModule } from "@swimlane/ngx-charts-dag";
import { TagInputModule } from "ngx-chips";
import { Ng4JsonEditorModule } from "angular4-jsoneditor";
import { SocketService } from "../../services/socket/socket.service";
import { WorkflowDetailsService } from "../../services/workflow-details/workflow-details.service";
import { PerisitenceService } from "../../services/persistence/perisitence.service";
import { APP_BASE_HREF } from "@angular/common";
import { AuthGuardService } from "../../services/authentication/auth-guard.service";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { AppConfig } from "../../app.config";

describe("CreateWorkflowComponent", () => {
  let component: CreateWorkflowComponent;
  let fixture: ComponentFixture<CreateWorkflowComponent>;

  class SocketServiceStub extends SocketService{
    
  }
  beforeEach(
    async(() => {
      TestBed.configureTestingModule({
        declarations: [
          CreateWorkflowComponent,  
          DialogOverviewDialog,
          WnameOverviewDialog, 
          JsonEditor, 
          SettingsDialog
        ],
        imports: [
          BrowserModule,
          AppRoutingModule,
          MaterialModule,
          ReactiveFormsModule,
          HttpModule,
          FormsModule,
          NgxChartsModule,
          NgxChartsDagModule,
          TagInputModule,
          Ng4JsonEditorModule
        ],
        providers: [
          AppConfig,
          WorkflowDetailsService,
          PerisitenceService,
          {provide: SocketService, useValue: SocketServiceStub },
          { provide: APP_BASE_HREF, useValue: "/" }
        ]        
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateWorkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
