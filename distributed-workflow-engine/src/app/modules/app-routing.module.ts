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

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path : 'projects', component: ProjectManagementComponent},
  {path : 'home', component: HomeComponent},
  {path : 'index', component: LoginHomeComponent},
  {path : 'profile', component: ProfileComponent},
  {path : 'reports', component: ReportsComponent},
  {path : 'addworkflow', component: CreateWorkflowComponent},
  {path : 'executeworkflow', component: ExecuteWorkflowComponent}  
];
@NgModule({ 
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }