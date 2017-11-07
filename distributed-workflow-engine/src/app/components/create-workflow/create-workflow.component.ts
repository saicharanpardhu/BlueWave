import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication/authentication.service';
 
import {Component, OnInit, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

import { FormControl } from '@angular/forms';
/**
 * @title Dialog Overview
 */ 
@Component({
  selector: 'app-create-workflow',
  templateUrl: './create-workflow.component.html',
  styleUrls: ['./create-workflow.component.css']
})
export class CreateWorkflowComponent implements OnInit{

  public tasks: Array<String> = ["git-clone","mvn-package"];
  // workflow: Array<Map<String,String>> = [];
  taskName: String;
  public taskType: String;
  map : Map<String,String> = new Map();
  ngOnInit() { 
    // console.log("Logged in: ", !(this.authentication.getAccessToken() === ''));
    // if(this.authentication.getAccessToken() === ''){
    //   this.router.navigate(['/index']);
    // }
  }

  constructor(
    public dialog: MatDialog, 
    private authentication : AuthenticationService,
    private router: Router) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(DialogOverviewDialog, {
      width: '300px',
      data: { taskName: this.taskName, tasks: this.tasks, taskType:this.taskType }
    });

    dialogRef.afterClosed().subscribe(result => {
      // var taskMap = new Map();
      // taskMap.set(result.taskName, result.taskType); 
      console.log(result.taskName, result.taskType, result,'The dialog was closed');
      this.map.set(result.taskName, result.taskType); 
      // this.workflow.push(taskMap);
      this.taskName = '';
    });
  }

}

@Component({
  selector: 'dialog-overview-dialog',
  templateUrl: 'dialog-overview-dialog.html',
  styleUrls : ['dialog-overview-dialog.css']
})
export class DialogOverviewDialog {
  tasks: Array<String> = ["git-clone","mvn-package"]; 
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
   
  onNoClick(): void { 
    this.dialogRef.close();
  }

}