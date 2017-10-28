package com.sr.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate; 
@Service
public class TaskProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	 
	public void sendMessage(String message) {
	    kafkaTemplate.send("testrun4", message);
	   
	}
}
