import { Project } from './../../model/project';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css']
})
export class ProjectManagementComponent implements OnInit {

  constructor(private authentication : AuthenticationService,
    private router: Router) { }

  Project1 = new Project("Default Project","This is your default project", ["Workflow1", "Workflow2"]);
  projects: Array<Project> = [
    this.Project1
  ]
  


  ngOnInit() {
    // console.log("Logged in: ", !(this.authentication.getAccessToken() === ''));
    // if(this.authentication.getAccessToken() === ''){
    //   this.router.navigate(['/index']);
    // }
  }

}
