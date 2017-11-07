import { Project } from './../../model/project';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css']
})
export class ProjectManagementComponent implements OnInit {

  constructor() { }

  Project1 = new Project("Default Project","This is your default project", ["Workflow1", "Workflow2"]);
  projects: Array<Project> = [
    this.Project1
  ]
  


  ngOnInit() {
    console.log("ProjectMAnagament");
  }

}
