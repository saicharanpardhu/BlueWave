package com.sr.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import com.sr.domain.*;

@Service
public class EngineServiceProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	 
	public void sendMessage(EngineModel engine) {
		
//		System.out.println("prodsdfsdddddddddddddddddddddd");
	    kafkaTemplate.send("task-response1","har-har-mahadev" );
	   
	}
}
