package com.distributedpipeline.reporting.messenger; 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedpipeline.reporting.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class ReportingServiceConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	@KafkaListener(topics = "testrun45", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void reportlistener(Report report) {
				logger.info(report.getMessage());
			    latch.countDown();
			} 
	
	@KafkaListener(topics = "report-topic", 
			  containerFactory = "kafkaListenerContainerFactory")
			public void messageListener(String message) {
			    logger.info(message);
			    latch.countDown();
			} 
}