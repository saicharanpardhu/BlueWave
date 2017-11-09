
import { Injectable, OnInit } from '@angular/core';

import { StompService } from 'ng2-stomp-service';
// import { CircleService } from './circle.service';
import { SockJS } from 'sockjs';
import { Stomp } from 'stompjs';
import { SocketMessage } from '../../model/socket-message';

import { Observable } from 'rxjs/Rx';
import { Subject }    from 'rxjs/Subject';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class SocketService {
  stompClient:any;
 private socketMessageSource = new Subject<SocketMessage>();
  socketUrl = 'http://172.23.238.216:8080/gs-guide-websocket';
 
  messageSubscription:any;
  socketMessage: String = "Default";
 socketMessages$ = this.socketMessageSource.asObservable();

 constructor(
    private stomp: StompService) {  
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
  
     this.messageSubscription = this.stomp.subscribe('/response',(message) => {
       console.log(message);
        this.socketMessage = message;
      });
}

 sendMessage(message: string) {
    let socketMessage = new SocketMessage(message);
    console.log(message);
    this.stomp.send('/app/topic/messages/',JSON.stringify({"message": message}));
    // this.socketMessageSource.next(socketMessage);
  }

 disconnect(){
    this.stomp.send('/app/topic/messages/',{"message":"logout notification"});
    this.stomp.disconnect().then(() => {
      console.log( 'Connection closed' )
    });
  }

}