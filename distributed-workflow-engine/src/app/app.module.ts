import { NgcFloatButtonModule } from 'ngc-float-button';
import { PerisitenceService } from "./services/persistence/perisitence.service";
import { MaterialModule } from "./modules/material.module";
import { RouterModule, Routes } from "@angular/router";
import { AppRoutingModule } from "./modules/app-routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule, Component } from "@angular/core";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./components/home/home.component";
import { WorkflowComponent } from "./components/workflow/workflow.component";
import { GetReportComponent } from "./components/reports/reports.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { LoginHomeComponent } from "./components/login-home/login-home.component";
import { StompService } from "ng2-stomp-service";
import { FormControl, ReactiveFormsModule, FormsModule } from "@angular/forms";
import { AuthenticationService } from "./services/authentication/authentication.service";
import { Http, HttpModule } from "@angular/http";
import {
  CreateWorkflowComponent,
  DialogOverviewDialog,
  WnameOverviewDialog,
  JsonEditor,
  SettingsDialog
} from "./components/create-workflow/create-workflow.component";
import { ExecuteWorkflowComponent } from "./components/execute-workflow/execute-workflow.component";
import { AuthGuardService } from "./services/authentication/auth-guard.service";
import { SocketService } from "./services/socket/socket.service";
import { APP_BASE_HREF, Location } from "@angular/common";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { NgxChartsDagModule } from "@swimlane/ngx-charts-dag";
import { TagInputModule } from "ngx-chips";
import { WorkflowDetailsService } from "./services/workflow-details/workflow-details.service";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { Ng4JsonEditorModule } from "angular4-jsoneditor";
import { ReportService } from "./services/report/report.service";
import { AppConfig } from "./app.config";
import {HttpInterceptor} from "./services/interceptor.service";
import { AutoSearchComponent } from './components/auto-search/auto-search.component'; 
import { WorkflowService } from './components/auto-search/auto-search.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    WorkflowComponent,
    ProfileComponent,
    LoginHomeComponent,
    CreateWorkflowComponent,
    DialogOverviewDialog,
    WnameOverviewDialog,
    ExecuteWorkflowComponent ,
    JsonEditor,SettingsDialog,GetReportComponent,
    ExecuteWorkflowComponent,
    JsonEditor,
    SettingsDialog,
    AutoSearchComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpModule,
    FormsModule,    
    NgxChartsModule, 
    NgxChartsModule,
    NgxChartsDagModule,
    TagInputModule,
    Ng4JsonEditorModule,
    NgcFloatButtonModule
  ],
  providers: [
    AuthenticationService,
    AppConfig,
    WorkflowDetailsService,
    PerisitenceService,
    AuthGuardService,
    SocketService,
    StompService,
    ReportService,
    HttpInterceptor,
    WorkflowService
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    DialogOverviewDialog,
    WnameOverviewDialog,
    JsonEditor,
    SettingsDialog
  ]
})
export class AppModule {}

export function getBaseLocation() {
  const paths: string[] = location.pathname.split("/").splice(1, 1);
  const basePath: string = (paths && paths[0]) || "";
  return "/" + basePath;
}
