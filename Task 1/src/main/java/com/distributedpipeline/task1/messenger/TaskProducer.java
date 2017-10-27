package com.distributedpipeline.task1.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate; 
@Service
public class TaskProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate2;
	 
	public void sendMessage(String message) {
	    kafkaTemplate.send("task1-response", message);
	   
	}
	public void sendReport(String message) {
		System.out.println("Sending report.." + message);
	    kafkaTemplate2.send("report-topic", message);
	   
	}
}
