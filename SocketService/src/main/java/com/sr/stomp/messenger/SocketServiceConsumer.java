package com.sr.stomp.messenger; 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
 
import com.sr.stomp.domain.Message;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class SocketServiceConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	 
	 
	  @Autowired
	  SimpMessagingTemplate template;
	  
	/* Topic for updating the status of tasks */  
	@KafkaListener(topics = "socket-topic", 
			  containerFactory = "kafkaListenerContainerFactory") 
			public void messageListener(String message) {
			    logger.info("Task update status" + message);
			    latch.countDown();
			    this.template.convertAndSend("/response", new Message(message));
			} 
	
	/* Topic for getting the total number of tasks */
	@KafkaListener(topics = "socket-number", 
			  containerFactory = "kafkaListenerContainerFactory") 
			public void numberListener(String number) {
			    logger.info(number);
			    latch.countDown();
			    this.template.convertAndSend("/number", new Message(number));
			    
			} 
	
	/* Topic for getting all the names of the tasks */
	@KafkaListener(topics = "socket-taskName", 
			  containerFactory = "kafkaListenerContainerFactory") 
			public void taskNameListener(String name) {
			    logger.info(name);
			    latch.countDown();
			    this.template.convertAndSend("/name", new Message(name));
			    
			} 
}