
import { AuthenticationService } from './../../services/authentication/authentication.service';
import { Component, OnInit } from '@angular/core';
import {MatToolbarModule,MatProgressSpinnerModule, MatSnackBar} from '@angular/material';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private authentication: AuthenticationService,
    private workflowService:WorkflowDetailsService,
    private snackBar:MatSnackBar) { }

  notif = true;
  ngOnInit() {
  } 

  logout(){
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('Email'); 
    this.snackBar.open("Logged out successfully",'');
  }
  viewmodeexit():void{
    this.workflowService.displayWorkflow=null;


  }
}
