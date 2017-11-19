import { SocketService } from './../../services/socket/socket.service';
import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { Router } from '@angular/router'; 
import { MatSnackBarConfig } from '@angular/material';

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
    this.value = 0;
    this.tasksComplete = 0;
    this.consoleOutput = [];
    this.tasks  = [];
    this.displayStepper = false;
    this.cardDisplay = false
  }
  response: any;
  value = 0;
  tasksComplete = 0;
  consoleOutput : Array<String> = [];
  tasks : Array<String> = [];
  displayStepper = false;
  cardDisplay = false;
  notLoaded = false;
  ngOnInit() {    

    // this.tasks = this.socketService.taskNames;
    this.socketService.socketMessages.subscribe( data => {

      this.tasksComplete += 1;
      console.log("Completed" + this.tasksComplete + " of " + this.socketService.socketNumber);
      // this.tasksComplete += 1;
      this.value = (this.tasksComplete/this.socketService.socketNumber)*100; 
      console.log(this.value); 
    }); 

    this.socketService.socketNames.subscribe( data => { 
            this.tasks.push(data);
            console.log('LENGTH',this.tasks.length);  
            if(this.tasks.length == this.socketService.socketNumber) this.displayStepper = true;
    });
  
  }
  isLinear = true;  
  sendMessage(message){
    this.socketService.sendMessage(message);
  }

  loadConsole(taskName){
    try{
      let taskOutput = this.socketService.socketConsoleMap.get(taskName);
      this.notLoaded = false;
      for(var i = 0; i <= taskOutput.length; i++) this.consoleOutput.push(taskOutput[i]);
      console.log("Console output: ",this.consoleOutput);
    }catch(e){
      this.notLoaded = true;
    }
    
  }

  ngOnDestroy(){ 
    this.value = 0;
    this.tasksComplete = 0;
    this.consoleOutput = null;
    this.tasks  = [];
    this.displayStepper = false;
    this.cardDisplay = false
  }
}
