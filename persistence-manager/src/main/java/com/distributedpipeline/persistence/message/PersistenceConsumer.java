package com.distributedpipeline.persistence.message;


import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.*;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.service.PersistenceServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class PersistenceConsumer {
	
	  final static Logger logger = Logger.getLogger(PersistenceConsumer.class);
	  private CountDownLatch latch = new CountDownLatch(1);

	  @Autowired
	  PersistenceProducer producer;
	  
	  @Autowired
	  PersistenceServiceImpl service;
	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	
	@KafkaListener(topics = "${kafka.topic.workflownametopersis}", 
			  containerFactory = "kafkaListenerContainerFactory")
			public void reportlistener(String workflow) throws WorkflowNotFoundException {
			    producer.sendMessage(service.getWorkflowByName(workflow));
			    latch.countDown();
			}
	
	@KafkaListener(topics = "${kafka.topic.detailstopersistence}",
            containerFactory = "kafkaJobDetailsContainerFactory")
          public void kafkalistener(JobIdDetails jobIdDetails) {
			service.addJobDetails(jobIdDetails);
              latch.countDown();
              logger.info("listening");
          }


}