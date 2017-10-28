package com.sr.messenger; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener; 

@Service
public class TaskConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 
	@KafkaListener(topics = "testrun4", 
			  containerFactory = "kafkaListenerContainerFactory")
			public void reportlistener(String message) {
			    System.out.println(message);
			    latch.countDown();
			} 
 
}