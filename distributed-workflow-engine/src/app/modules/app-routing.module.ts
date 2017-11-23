import { ExecuteWorkflowComponent } from './../components/execute-workflow/execute-workflow.component';
import { ProjectDetailsComponent } from './../components/project-details/project-details.component';
import { GetReportComponent } from './../components/reports/reports.component';
import { ProfileComponent } from './../components/profile/profile.component';
import { LoginHomeComponent } from './../components/login-home/login-home.component';
import { HomeComponent } from './../components/home/home.component';
import { ProjectManagementComponent } from './../components/project-management/project-management.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CreateWorkflowComponent } from '../components/create-workflow/create-workflow.component';
import { AuthGuardService } from '../services/authentication/auth-guard.service';
import { WorkflowComponent } from '../components/workflow/workflow.component';
import { WorkflowDetailsComponent } from '../components/workflow-details/workflow-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path : 'projects', component: ProjectManagementComponent, canActivate: [AuthGuardService]},
  {
    path : 'home', 
    component: HomeComponent, canActivate: [AuthGuardService]
  },
  {path : 'index', component: LoginHomeComponent},
  {path : 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path : 'reports', component: GetReportComponent, canActivate: [AuthGuardService]},
  {path : 'addworkflow', component: CreateWorkflowComponent, canActivate: [AuthGuardService]},
  {path : 'workflows', component: WorkflowComponent, canActivate: [AuthGuardService]},
  {path : 'workflows/workflowdetails', component: WorkflowDetailsComponent, canActivate: [AuthGuardService]}, 
  {path : 'executeworkflow', component: ExecuteWorkflowComponent,canActivate: [AuthGuardService]},
  {path : 'viewworkflow', component: CreateWorkflowComponent,canActivate: [AuthGuardService]} 
];
@NgModule({ 
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }