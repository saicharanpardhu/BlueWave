package com.distributedpipelilne.buildAgent.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.buildAgent.domain.Output;



@Service
public class BuildProducer {
	
	@Autowired
	BuildConsumer buildConsumer;
	
	
	@Autowired
	private KafkaTemplate<String, Output> kafkaTemplate;
	
	@Autowired
	Output output;
	
	
	 
	public void sendMessage() {
		output.setJobId(buildConsumer.getJobid());
		output.setTaskName(buildConsumer.getTaskname());
	    kafkaTemplate.send("build", output);
	   
	}

}
