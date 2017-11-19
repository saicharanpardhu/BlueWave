import { WorkflowDetailsService } from './services/workflow-details/workflow-details.service';
import { AuthenticationService } from './services/authentication/authentication.service';
import { SocketService } from './services/socket/socket.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app'; 

  notif = true;
  constructor(private snackBar:MatSnackBar,
              private socketService: SocketService,
            private authService : AuthenticationService,
          private workflowService: WorkflowDetailsService){}
  notifications = [];        
  ngOnInit(){ 
    localStorage.clear();
    this.socketService.socketMessages.subscribe( data => {
      let config = new MatSnackBarConfig();
      config.duration = 1000;
      this.snackBar.open(data.toString(),'');
      console.log(data);
      this.notifications.push(data);
  });
  } 
  sideNavopened(){
    this.notif = !this.notif;
  }

  viewmodeexit():void{
    this.workflowService.displayWorkflow=null; 
  }
}
 
 