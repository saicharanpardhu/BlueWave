import { WorkflowDetailsService } from "./services/workflow-details/workflow-details.service";
import { AuthenticationService } from "./services/authentication/authentication.service";
import { SocketService } from "./services/socket/socket.service";
import { Component, OnInit } from "@angular/core";
import { MatSnackBar, MatSnackBarConfig } from "@angular/material";
import {
  trigger,
  query,
  stagger,
  keyframes,
  transition,
  style,
  animate,
  state
} from "@angular/animations";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  title = "app";
  notif = true;
  constructor(
    private snackBar: MatSnackBar,
    private socketService: SocketService,
    private authService: AuthenticationService,
    private workflowService: WorkflowDetailsService
  ) {}
  notifications = [];
  ngOnInit() {
    this.socketService.socketMessages.subscribe(data => {
      let config = new MatSnackBarConfig();
      config.duration = 1000;
      this.snackBar.open(
        this.socketService.workFlowName + " update: " + data.toString(),
        "Close"
      );
      console.log(data);
      this.notifications.push(data);
    });
  }

  logout() {
    this.authService.logout();
  }

  clearNotifications() {
    this.notifications = [];
  }

  sideNavopened() {
    this.notif = !this.notif;
  }

  viewmodeexit(): void {
    this.workflowService.displayWorkflow = null;
  }
}
