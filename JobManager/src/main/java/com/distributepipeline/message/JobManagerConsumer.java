package com.distributepipeline.message;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributepipeline.domain.JobIdDetails;
import com.distributepipeline.domain.ModelToSocket;
import com.distributepipeline.domain.WorkFlow;


@Service
public class JobManagerConsumer {
	
	private static Logger logger = LogManager.getLogger("JobManagerConsumer.class");

	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 @Autowired
	 ModelToSocket modelToSocket;
	 
	 @Autowired
	 JobIdDetails jobIdDetails;
	 
	 @Autowired
	 WorkFlow workFlow;
	 
	 @Autowired
	 JobManagerProducer jobManagerProducer;
	 

		
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	//Kafka Listener to get workFlow from Persistence Manager
	
	@KafkaListener(topics = "${kafka.topic.workflowfrompersistence}", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(WorkFlow workFlow) throws IOException, InterruptedException {
		
		logger.info("workflow recieved from persistence");
		
		//calling method to send total number of task to workflow-details(in angular)
		if(workFlow.getTasks()!=null)
		jobManagerProducer.sendNumberOfTasks(workFlow.getTasks().size());
		
		//calling method to send tasknames to workflow-details(in angular)
		
		for(String key:workFlow.getTasks().keySet()) {
//			modelToSocket.setUserName(jobIdDetails.getUserName());
			logger.info("usernameconsumer "+jobIdDetails.getUserName());
			modelToSocket.setTaskName(key);
			logger.info("tasknameconsumer "+key);
			jobManagerProducer.sendTaskName(modelToSocket);
		}	
	
		//calling method to send complete workflow to engine
		
		jobManagerProducer.triggerEngine(workFlow);
		
	}
}
