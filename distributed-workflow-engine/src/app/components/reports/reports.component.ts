import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  constructor(
    private authentication : AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    // console.log("Logged in: ", !(this.authentication.getAccessToken() === ''));
    // if(this.authentication.getAccessToken() === ''){
    //   this.router.navigate(['/index']);
    // }
  }

}
