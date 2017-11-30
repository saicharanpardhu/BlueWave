package com.sr.stomp.messenger; 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.stomp.domain.ConsoleModel;
import com.sr.stomp.domain.JobIdDetails;
import com.sr.stomp.domain.Message;
import com.sr.stomp.domain.SocketModel;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.kafka.annotation.KafkaListener;
//<!----Consumer: consumes String and Report -->
@Service
public class SocketServiceConsumer {
    
//      @Autowired
//      Message message;
    
      private CountDownLatch latch = new CountDownLatch(1);
      public CountDownLatch getLatch() {
        return latch;
      }
    
      private static Logger logger = LogManager.getLogger("MethodLogger.class");
     
     
      @Autowired
      SimpMessagingTemplate template;
      
      @Autowired
      SimpMessagingTemplate template1;
      
    /* Topic for updating the status of tasks */  
    @KafkaListener(topics = "socket-topic", 
              containerFactory = "kafkaMessageListenerContainerFactory") 
            public void messageListener(Message message) {
                logger.info("Task update status" + message);
                latch.countDown();
                String username=message.getUserName();
                System.out.println("USERNAME from resultProcessor "+ message.getOutput() + " " + message.getUserName());
                System.out.println("/response/"+username);
                this.template.convertAndSend("/response/"+username, message);
            } 
    
    /* Topic for updating the jobId */  
    @KafkaListener(topics = "jobIdToSocket", 
              containerFactory = "kafkaJobIdListenerContainerFactory") 
            public void jobIdListener(JobIdDetails message) {
                logger.info("Job Id: " + message.getJobId());
                logger.info("WorkflowName : " + message.getWorkFlowName());
                latch.countDown();
                String username=message.getUserName();
                System.out.println("job id topic: "+ "/workflow/"+username); 
                this.template1.convertAndSend("/workflow/"+username, message);
            } 
  
    /* Topic for getting the total number of tasks */
    @KafkaListener(topics = "console", 
              containerFactory = "kafkaConsoleListenerContainerFactory") 
            public void consoleListener(ConsoleModel message) {
                logger.info(message);
                latch.countDown(); 
                System.out.println("*********************************************CONSOLE OUTPUT START****************************************************");
                System.out.println("Console "+ message.getConsole());
                System.out.println("*********************************************CONSOLE OUTPUT END****************************************************");
                String myString = message.getConsole();
                Scanner scanner = new Scanner(myString);
                System.out.println("*******************CONSOLE UESRNAME**************** /console/"+message.getUserName());
                System.out.println("*******************CONSOLE TASKNAME**************** /console/"+message.getTaskName());
                while (scanner.hasNextLine()) {
                  String line = scanner.nextLine();
                  System.out.println();
                  System.out.println(line);
                  this.template.convertAndSend("/console/"+message.getUserName(), new ConsoleModel(message.getTaskName(),message.getUserName(),line));
                }
                scanner.close();
                
                
                
            } 
    
    /* Topic for getting the total console of tasks */
    @KafkaListener(topics = "socket-number", 
              containerFactory = "kafkaListenerContainerFactory") 
            public void numberListener(String message) {
                logger.info(message);
                latch.countDown(); 
                String[] args = message.split(" ");
                System.out.println("Data from tasknumber: "+ args[0] + " number: " + args[1]);
                this.template.convertAndSend("/number/"+args[0], args[1]);
                
            } 
    
    /* Topic for getting the total console of tasks */
//    @KafkaListener(topics = "jobId", 
//              containerFactory = "kafkaListenerContainerFactory") 
//            public void jobIdListener(String message) {
//                logger.info(message);
//                latch.countDown(); 
//                String[] args = message.split(" ");
//                System.out.println("USERNAME from jobId: "+ args[0]);
//                logger.info("/workflow/"+args[0]);
//                this.template1.convertAndSend("/workflow/"+args[0], args[1]);
//                
//            } 
    
    /* Topic for getting all the names of the tasks */
    @KafkaListener(topics = "socket-taskName", 
              containerFactory = "kafkaSocketMessageListenerContainerFactory") 
            public void taskNameListener(SocketModel message) {
                logger.info(message);
                latch.countDown();
                String username=message.getUserName();
                System.out.println("/name/"+username + " " + message.getTaskName());
                this.template.convertAndSend("/name/"+username, message);
                
            } 
}