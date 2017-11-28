import { PerisitenceService } from './services/persistence/perisitence.service';
import { MaterialModule } from './modules/material.module';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './modules/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core'; 
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ProjectManagementComponent } from './components/project-management/project-management.component';
import { WorkflowComponent } from './components/workflow/workflow.component';
import { WorkflowExecutionComponent } from './components/workflow-execution/workflow-execution.component';
import { GetReportComponent } from './components/reports/reports.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginHomeComponent } from './components/login-home/login-home.component'; 
import { StompService } from 'ng2-stomp-service'
import { FormControl , ReactiveFormsModule, FormsModule} from '@angular/forms';
import { AuthenticationService } from './services/authentication/authentication.service';
import { Http, HttpModule } from '@angular/http';
import { CreateWorkflowComponent, DialogOverviewDialog,WnameOverviewDialog, JsonEditor,SettingsDialog } from './components/create-workflow/create-workflow.component';
import { ExecuteWorkflowComponent } from './components/execute-workflow/execute-workflow.component'; 
import { AuthGuardService } from './services/authentication/auth-guard.service';
import { SocketService } from './services/socket/socket.service';
import { APP_BASE_HREF, Location } from '@angular/common';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxChartsDagModule } from '@swimlane/ngx-charts-dag';
import { TagInputModule } from 'ngx-chips';
import { WorkflowDetailsComponent } from './components/workflow-details/workflow-details.component';
import { WorkflowDetailsService } from './services/workflow-details/workflow-details.service';

import { Ng4JsonEditorModule } from 'angular4-jsoneditor'; 
import { LayoutModule } from 'ng2-flex-layout';
import { ReportService } from './services/report/report.service';
import { LandingPageComponent } from './components/landing-page/landing-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent, 
    ProjectManagementComponent,
    WorkflowComponent,
    WorkflowExecutionComponent,
    FooterComponent,
    NavbarComponent,
    ProfileComponent,
    LoginHomeComponent,
    CreateWorkflowComponent,
    DialogOverviewDialog,
    WnameOverviewDialog,
    ExecuteWorkflowComponent ,
    WorkflowDetailsComponent,
    JsonEditor,SettingsDialog,GetReportComponent, LandingPageComponent
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule, 
    HttpModule,
    FormsModule,
    LayoutModule,    
    NgxChartsModule, 
    NgxChartsDagModule,
    TagInputModule,
    Ng4JsonEditorModule
  ],
  providers: [AuthenticationService, WorkflowDetailsService, PerisitenceService, AuthGuardService, SocketService, StompService, ReportService ],
  bootstrap: [AppComponent],
  entryComponents:[DialogOverviewDialog,WnameOverviewDialog,JsonEditor,SettingsDialog]
})
export class AppModule { }
 
 export function getBaseLocation() {
    const paths: string[] = location.pathname.split('/').splice(1, 1);
    const basePath: string = (paths && paths[0]) || '';
    return '/' + basePath;
}
