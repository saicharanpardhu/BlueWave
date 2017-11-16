import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { Http } from '@angular/http';

import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router'; 
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authentication : AuthenticationService,
    private router: Router, private persistence: PerisitenceService) { }

  ngOnInit() {  
  }
  sendWorkFlow(){
    this.persistence.sendWorkFlow();
  }
  executeWorkflow(){ 
    this.persistence.triggerEngine("git-process2");
  }
}
