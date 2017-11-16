
import { AuthenticationService } from './../../services/authentication/authentication.service';
import { Component, OnInit } from '@angular/core';
import {MatToolbarModule,MatProgressSpinnerModule} from '@angular/material';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private authentication: AuthenticationService,private workflowService:WorkflowDetailsService) { }

  notif = true;
  ngOnInit() {
  }

  logout(){
    this.authentication.setAccessToken(); 
  }
  viewmodeexit():void{
    this.workflowService.displayWorkflow=null;


  }
}
