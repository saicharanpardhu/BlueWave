package com.distributedworkflowengine.taskscheduler.messaging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.taskscheduler.domain.Agent;
import com.distributedworkflowengine.taskscheduler.domain.Model;
import com.distributedworkflowengine.taskscheduler.domain.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class TaskSchedulerConsumer {
	
	private static Logger logger=LogManager.getLogger("TaskSchedulerConsumer.class");

	@Autowired
	TaskSchedulerProducer taskSchedulerProducer;
	@Autowired
	private KafkaTemplate<String, Agent> kafkaTemplate;
	@Autowired
	Agent agent;
	
	@Autowired
	Agent agent2;
	private Model model;
	private Map<String, Task> taskName;
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Map<String, Task> getTaskName() {
		return taskName;
	}



	public void setTaskName(Map<String, Task> taskName) {
		this.taskName = taskName;
	}

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	    return latch;
	}
		
		// method to consume model object through kafka 
		
		@KafkaListener(topics = "${kafka.topic.taskscheconsumer}", 
				  containerFactory = "engineKafkaListenerContainerFactory")
				public void reportlistener(Model model1) throws JsonProcessingException {
			
			
			for (Map.Entry<String,Task> entry : model1.getListOfTasks().entrySet()) 
			{
				logger.info(model1.getListOfTasks());
				logger.info(model1.getUserName());
				agent.setJobId(model1.getJobId());
				agent.setTaskname(entry.getKey());
				agent.setUserName(model1.getUserName());
				if(model1.getListOfTasks().get(entry.getKey()).getInput()!=null)
				agent.setInput(model1.getListOfTasks().get(entry.getKey()).getInput());
	           
				ObjectMapper mapperObj2 = new ObjectMapper();
				agent.setType(entry.getValue().getType());
				logger.info(mapperObj2.writeValueAsString(agent));
				
				logger.info("DAta"+entry.getKey());
				agent.setWorkFlowName(model1.getWorkFlowName());
				if(model1.getListOfTasks().get(entry.getKey()).getCommands()!=null)
					agent.setCommands(model1.getListOfTasks().get(entry.getValue()).getCommands());
				if(model1.getListOfTasks().get(entry.getKey()).getFolderPath()!=null)
					agent.setFolderPath(model1.getListOfTasks().get(entry.getValue()).getFolderPath());
				kafkaTemplate.send(entry.getValue().getType(),agent);
			
		}
						
		    latch.countDown();
				}


}
