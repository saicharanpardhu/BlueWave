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
  constructor(private snackBar:MatSnackBar,
              private socketService: SocketService){}
  ngOnInit(){
    this.openSnackBar();
    this.socketService.socketMessages.subscribe( data => {
      let config = new MatSnackBarConfig();
      config.duration = 1000;
      this.snackBar.open(data.toString(),'');
      console.log(data);
  });
  }

  openSnackBar(){
    this.snackBar.openFromComponent(SnackBarComponent,{
      duration: 500
    });
  }
}

@Component({
  selector: 'snack-bar-component',
  template: '<span>Snackbar</span>',
  styles: [`span { color: hotpink; }`],
})
export class SnackBarComponent {

}
 