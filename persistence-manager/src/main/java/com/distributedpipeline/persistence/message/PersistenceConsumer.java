package com.distributedpipeline.persistence.message;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	
	@KafkaListener(topics = "${kafka.topic.detailstopersistence}",
            containerFactory = "kafkaJobDetailsContainerFactory")
          public void kafkalistener(JobIdDetails jobIdDetails) {
			service.addJobDetails(jobIdDetails);
              latch.countDown();
              logger.info("listening");
          }
	
	// Heat Map Generation Data //
	
	@KafkaListener(topics = "${kafka.topic.countStatus}",
            containerFactory = "kafkaListenerContainerFactory")
          public void kafkalistener(String heatMapData) throws WorkflowNotFoundException, ParseException {
			String[] data = heatMapData.split("&");
		
			if(data.length > 0) {
				String userName = data[0];
				String workFlowName = data[1];
				logger.info(userName);
				logger.info(workFlowName);
				logger.info(data[2]);
				Timestamp executionTime = Timestamp.valueOf(data[2]);
				logger.info(executionTime);
				Workflow workflow = service.getWorkflowByNameAndUserName(workFlowName,userName);
			    String freq= workflow.getFrequency();
			    
			    logger.info("freq"+freq);
			    
			    int frequency = 0;
			    if(freq==null) {
			    	frequency=1;
			    }
			    else {
				    frequency= Integer.parseInt(freq);
				    logger.info(frequency);
			    	frequency=frequency+1;
			    }
			    freq = String.valueOf(frequency);
			    workflow.setFrequency(freq);
			    List<Date> date1 = null;
			    if(workflow.getExecutionTime() != null) {
    				date1 = workflow.getExecutionTime();
			    }
	            else {
	                date1  = new ArrayList<Date>();
	            }    
				date1.add(executionTime);				
				workflow.setExecutionTime(date1);
				logger.info(executionTime);
				logger.info(date1);
				service.updateWorkflow(workflow);
				
	              latch.countDown();
	              logger.info("listening");
				}
			}


}