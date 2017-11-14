import { ReportsComponent } from './../components/reports/reports.component';
import { ProfileComponent } from './../components/profile/profile.component';
import { LoginHomeComponent } from './../components/login-home/login-home.component';
import { HomeComponent } from './../components/home/home.component';
import { ProjectManagementComponent } from './../components/project-management/project-management.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CreateWorkflowComponent } from '../components/create-workflow/create-workflow.component';
import { ExecuteWorkflowComponent } from '../components/execute-workflow/execute-workflow.component';
import { AuthGuardService } from '../services/authentication/auth-guard.service';
import { WorkflowDetailsComponent } from '../components/workflow-details/workflow-details.component';
import { ProjectDetailsComponent } from '../components/project-details/project-details.component'

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path : 'projects', component: ProjectManagementComponent, canActivate: [AuthGuardService]},
  {
    path : 'home', 
    component: HomeComponent
  },
  {path : 'index', component: LoginHomeComponent},
  {path : 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path : 'reports', component: ReportsComponent, canActivate: [AuthGuardService]},
  {path : 'addworkflow', component: CreateWorkflowComponent, canActivate: [AuthGuardService]},
  {path : 'executeworkflow', component: ExecuteWorkflowComponent, canActivate: [AuthGuardService]} , 
  {path : 'addworkflow', component: CreateWorkflowComponent},
  {path : 'workflow' , component: ProjectDetailsComponent},
  {path : 'executeworkflow', component: ExecuteWorkflowComponent, canActivate: [AuthGuardService]},  
  {path : 'workflow/workflowdetails', component: WorkflowDetailsComponent ,canActivate: [AuthGuardService] } 
  
];
@NgModule({ 
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }