package com.distributedpipeline.persistence.message;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.distributedpipeline.persistence.controller.PersistenceController;
import com.distributedpipeline.persistence.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
@Service
public class PersistenceProducer {
	 
	final static Logger logger = Logger.getLogger(PersistenceProducer.class);
	
	@Autowired
	private KafkaTemplate<String, Workflow> kafkaTemplate;
	

@Value("${kafka.topic.workflowfrompersistence}")
private String topic1;
	 
	public void sendMessage(Workflow workFlow) {
	    kafkaTemplate.send(topic1, workFlow);

		logger.info("producing");
	   
	}
	

}
