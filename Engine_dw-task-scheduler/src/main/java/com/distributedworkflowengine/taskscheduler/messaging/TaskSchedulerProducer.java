package com.distributedworkflowengine.taskscheduler.messaging;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.taskscheduler.domain.Agent;
import com.distributedworkflowengine.taskscheduler.domain.Model;
import com.distributedworkflowengine.taskscheduler.domain.Task;

import org.springframework.kafka.core.KafkaTemplate;


@Service
public class TaskSchedulerProducer {
	@Autowired
	TaskSchedulerConsumer consumer;
	@Autowired
	Agent agent;
	@Autowired
	private KafkaTemplate<String, Agent> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate2;
	 
	private Map<String,Task> mp;

	//<!-- method to send task to a particular agent
	
	public void sendMessage(String taskName,Agent agent) {
		

		kafkaTemplate.send(taskName, agent);   

}
	
	
	
	
}