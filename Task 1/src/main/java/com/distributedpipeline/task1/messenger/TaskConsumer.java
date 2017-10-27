package com.distributedpipeline.task1.messenger; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedpipeline.task1.messenger.TaskProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener; 

@Service
public class TaskConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	@Autowired
	TaskProducer taskProducer;
	  
	@KafkaListener(topics = "task1-topic", 
			  containerFactory = "kafkaListenerContainerFactory")
			public void reportlistener(String message) {
			    System.out.println(message); 
			    taskProducer.sendMessage(message.toUpperCase());
			    latch.countDown();
			} 
	
	
 
}