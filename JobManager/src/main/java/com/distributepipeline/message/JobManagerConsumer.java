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
		jobManagerProducer.triggerEngine(workFlow);
		
	}
}
