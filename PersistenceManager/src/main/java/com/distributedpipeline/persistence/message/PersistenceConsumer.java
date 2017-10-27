package com.distributedpipeline.persistence.message;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedpipeline.persistence.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class PersistenceConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	
	@KafkaListener(topics = "testrun5", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void reportlistener(Report report) {
			    System.out.println(report.getUserid());
			    latch.countDown();
			}


}