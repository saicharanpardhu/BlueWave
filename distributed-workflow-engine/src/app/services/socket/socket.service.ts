import { Http } from '@angular/http'; 
import { Injectable, OnInit } from '@angular/core'; 
import { StompService } from 'ng2-stomp-service'; 
import { SocketMessage } from '../../model/socket-message'; 
import { Observable } from 'rxjs/Rx';
import { Subject }    from 'rxjs/Subject'; 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class SocketService {
  stompClient:any;
  socketMessageSource = new Subject<String>();
  socketUrl = 'http://172.23.238.216:8080/gs-guide-websocket'; 
  socket: any;
  messageSubscription:any;
  socketMessage: String = "Default";
  socketMessages = this.socketMessageSource.asObservable();
  numberSubscription:any; 
  socketNumber:number;
  

  nameSubscription : any; 
  taskNames : Array<String> = [];
  socketNameSource = new Subject<String>();
  socketNames = this.socketNameSource.asObservable();

 constructor(
    private http:Http,
    private stomp: StompService) {   
    this.connect();
  } 
connect(){
  this.stomp.configure({
    host: this.socketUrl,
    debug: true,
    queue: {'init': false}
  });
  this.stomp.startConnect().then(() => {
    this.stomp.done('init'); 
    this.subscribe(); 
  });
}
subscribe(){
  if(this.messageSubscription != null)
        this.messageSubscription.unsubscribe();
    
  
     this.messageSubscription = this.stomp.subscribe('/response',(response) => {
        let temp : String = response.message;
        this.socketMessageSource.next(temp);
        console.log(temp);
      }); 

      if(this.numberSubscription != null)
      this.numberSubscription.unsubscribe();
  

   this.numberSubscription = this.stomp.subscribe('/number',(response) => {
      let temp : String = response.message;
      this.socketNumber = response.message;
      console.log(this.socketNumber); 
    }); 

    if(this.nameSubscription != null)
    this.nameSubscription.unsubscribe();


 this.nameSubscription = this.stomp.subscribe('/name',(response) => {
    let temp : String = response.message; 
    console.log("RECEIEVED TASKNAME: " , temp); 
    this.socketNameSource.next(temp); 
  }); 
}
 
 sendMessage(message: string) {
    let socketMessage = new SocketMessage(message);
    console.log(message);
    this.stomp.send('/app/topic/messages',{"message":message}); 
  }

 disconnect(){
    this.stomp.send('/app/topic/messages',{"message":"logout notification"});
    this.stomp.disconnect().then(() => {
      console.log( 'Connection closed' )
    });
  }

}