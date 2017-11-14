package com.sr.stomp.controller;
 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller; 
import com.sr.stomp.domain.Message;


/* Controller class mapping messages to a socket response topic */
@Controller
public class SocketController {
	
	/*Both of the mappings are for testing if the socket is working or not, not used in applicaiton */
	
	/*Mapping Socket messages to another socket messaging topic */
	
    @MessageMapping("/topic/messages")
    @SendTo("/response")
    @CrossOrigin(origins = "*")
    public Message greeting(Message message) throws Exception { 
        System.out.println("Received at socket: " + message.getMessage() + "!");
        return new Message("Received at socket: " + message.getMessage() + "!");
    }
    
    /* Mapping Http post requests to Socket topics for testing */
    
    @PostMapping("/response/snack")
    @SendTo("/response")
    @CrossOrigin(origins = "*")
    public Message greetingSnack(Message message) throws Exception { 
        System.out.println("Received at socket: " + message + "!");
        return new Message("Received at socket: " + message + "!");
    }
    
     
}