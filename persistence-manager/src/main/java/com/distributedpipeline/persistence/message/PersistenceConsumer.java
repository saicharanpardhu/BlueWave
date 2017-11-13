package com.distributedpipeline.persistence.message;


import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.*;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.service.PersistenceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class PersistenceConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  @Autowired
	  PersistenceProducer producer;
	  
	  @Autowired
	  PersistenceServiceImpl service;
	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	
	@KafkaListener(topics = "workFlowName", 
			  containerFactory = "kafkaListenerContainerFactory")
			public void reportlistener(String workflow) throws WorkflowNotFoundException {
			    System.out.println(workflow);
			    producer.sendMessage(service.getWorkflowByName(workflow));
			    latch.countDown();
			}


}