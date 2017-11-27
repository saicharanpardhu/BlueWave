package com.distributedpipeline.persistence.message;


import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.*;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.service.PersistenceService;
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
	  private PersistenceService service;
	  
	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	
	@KafkaListener(topics = "${kafka.topic.workflownametopersis}", 
			  containerFactory = "kafkaListenerContainerFactory")

	public void reportlistener(String UserWorkflow) throws WorkflowNotFoundException {
		
		String str[]=UserWorkflow.split("-");
		String userName=str[0];
		String workflowName=str[1];
		logger.info(workflowName);
		Workflow workflow = service.getWorkflowByNameAndUserName(workflowName, userName);
		if(workflow!=null) {
		    producer.sendMessage(workflow);
		    logger.info(workflow.getOwner());
	    	latch.countDown();
		}
			else logger.info("workflow doesn't exist");
//		public void reportlistener(String workflow) throws WorkflowNotFoundException {
//		    logger.info(workflow);
//		    producer.sendMessage(service.getWorkflowByName(workflow));
//		    latch.countDown();
	}

			public void reportlistener(String UserWorkflow) throws WorkflowNotFoundException {
		
			String str[]=UserWorkflow.split("-");
			String userName=str[0];
			String workflowName=str[1];
			logger.info(workflowName);
			Workflow workflow = service.getWorkflowByNameAndUserName(workflowName, userName);
			if(workflow!=null) {
			    producer.sendMessage(workflow);
			    logger.info(workflow.getOwner());
		    	latch.countDown();
			}
				else logger.info("workflow doesn't exitst");
			}

	
	@KafkaListener(topics = "${kafka.topic.detailstopersistence}",
            containerFactory = "kafkaJobDetailsContainerFactory")
          public void kafkalistener(JobIdDetails jobIdDetails) {
			service.addJobDetails(jobIdDetails);
              latch.countDown();
              logger.info("listening");
          }


}