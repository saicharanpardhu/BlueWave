package com.distributedpipelilne.cloneagent.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.cloneagent.model.Output;


@Service
public class CloneAgentProducer {
	
	@Autowired
	CloneAgentConsumer agentConsumer;
	
	@Autowired
	private KafkaTemplate<String, Output> kafkaTemplate;
	
	@Autowired
	Output output;
	
	
	 
	public void sendMessage() {
		output.setJobId(agentConsumer.getJobid());
		output.setTaskName(agentConsumer.getTaskname());
	    kafkaTemplate.send("git", output);
	   
	}

}
