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
import { CreateWorkflowComponent, DialogOverviewDialog } from './components/create-workflow/create-workflow.component';
import { ExecuteWorkflowComponent } from './components/execute-workflow/execute-workflow.component'; 
import { AuthGuardService } from './services/authentication/auth-guard.service';
import { SocketService } from './services/socket/socket.service';
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
    ExecuteWorkflowComponent,
    SnackBarComponent
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpModule,
    FormsModule
  ],
  providers: [AuthenticationService, PerisitenceService, AuthGuardService, SocketService, StompService],
  bootstrap: [AppComponent],
  entryComponents:[DialogOverviewDialog, SnackBarComponent]
})
export class AppModule { }
 