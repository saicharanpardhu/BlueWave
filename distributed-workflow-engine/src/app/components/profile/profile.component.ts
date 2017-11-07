import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private authentication : AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    // console.log("Logged in: ", !(this.authentication.getAccessToken() === ''));
    // if(this.authentication.getAccessToken() === ''){
    //   this.router.navigate(['/index']);
    // }
  }

}
