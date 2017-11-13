import { PerisitenceService } from './services/persistence/perisitence.service';
import { MaterialModule } from './modules/material.module';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './modules/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core'; 
import { AppComponent, SnackBarComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ProjectManagementComponent } from './components/project-management/project-management.component';
import { WorkflowComponent } from './components/workflow/workflow.component';
import { WorkflowExecutionComponent } from './components/workflow-execution/workflow-execution.component';
import { ReportsComponent } from './components/reports/reports.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginHomeComponent } from './components/login-home/login-home.component'; 
import { StompService } from 'ng2-stomp-service'
import { FormControl , ReactiveFormsModule, FormsModule} from '@angular/forms';
import { AuthenticationService } from './services/authentication/authentication.service';
import { Http, HttpModule } from '@angular/http';
import { CreateWorkflowComponent, DialogOverviewDialog,WnameOverviewDialog } from './components/create-workflow/create-workflow.component';
import { ExecuteWorkflowComponent } from './components/execute-workflow/execute-workflow.component'; 
import { AuthGuardService } from './services/authentication/auth-guard.service';
import { SocketService } from './services/socket/socket.service';
import { APP_BASE_HREF, Location } from '@angular/common';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxChartsDagModule } from '@swimlane/ngx-charts-dag';
import { TagInputModule } from 'ngx-chips';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProjectManagementComponent,
    WorkflowComponent,
    WorkflowExecutionComponent,
    ReportsComponent,
    FooterComponent,
    NavbarComponent,
    ProfileComponent,
    LoginHomeComponent,
    CreateWorkflowComponent,
    DialogOverviewDialog,
    WnameOverviewDialog,
    ExecuteWorkflowComponent,
    SnackBarComponent
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
    TagInputModule
  ],
  providers: [AuthenticationService, PerisitenceService, AuthGuardService, SocketService, StompService,
    {
      provide: APP_BASE_HREF,
      useFactory: getBaseLocation
    }
  ],
  bootstrap: [AppComponent],
  entryComponents:[DialogOverviewDialog, SnackBarComponent,WnameOverviewDialog]
})
export class AppModule { }
 
 export function getBaseLocation() {
    const paths: string[] = location.pathname.split('/').splice(1, 1);
    const basePath: string = (paths && paths[0]) || '';
    return '/' + basePath;
}
