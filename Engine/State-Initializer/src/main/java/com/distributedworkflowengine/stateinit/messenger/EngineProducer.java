package com.distributedworkflowengine.stateinit.messenger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.Trigger;

import org.springframework.kafka.core.KafkaTemplate;
//<!----Reporting Service Producer: Produces Report and String-->
@Service
public class EngineProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	  
	
	 
	public void sendMessage(String jobId) {

	    kafkaTemplate.send("StateToJobSche",jobId );  
	} 
	
	
}
