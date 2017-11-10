import { SocketService } from './../../services/socket/socket.service';
import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-execute-workflow',
  templateUrl: './execute-workflow.component.html',
  styleUrls: ['./execute-workflow.component.css']
})
export class ExecuteWorkflowComponent implements OnInit {

  constructor(private _formBuilder : FormBuilder,
  private persistenceService: PerisitenceService,
  private authentication : AuthenticationService,
  private router: Router, private socketService: SocketService
  ) { 
 
  }
  response: any;
  ngOnInit() { 
    console.log(this.response);
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });  
  }
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  
  sendMessage(message){
    this.socketService.sendMessage(message);
  }

  ngOnDestroy(){ 
  }
}
