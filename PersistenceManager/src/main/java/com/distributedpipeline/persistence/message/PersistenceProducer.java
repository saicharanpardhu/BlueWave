package com.distributedpipeline.persistence.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.persistence.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
@Service
public class PersistenceProducer {
	@Autowired
	private KafkaTemplate<String, Workflow> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Tasks> kafkaTemplate1;
	
	@Autowired
	private KafkaTemplate<String, TaskLibrary> kafkaTemplaten;
	
//	@Autowired
//	private KafkaTemplate<String, Integer> kafkaTemplate2;
	 
	public void sendMessage(Workflow model) {
	    kafkaTemplate.send("persistence45", model);
	   
	}
	
	public void sendTask(Tasks task) {
	    kafkaTemplate1.send("Tasks", task);
	   
	}
	
	public void sendTask(TaskLibrary taskLibrary) {
	    kafkaTemplaten.send("TaskLibrary", taskLibrary);
	   
	}
//	public void sendInt(int a,int b) {
//	    kafkaTemplate2.send("persistence45",a);
//	   
//	}
//	
//	public void sendReport(String message) {
//        System.out.println("Sending report.." + message);
//        kafkaTemplate1.send("report-topic", message);
//       
//    }
}
