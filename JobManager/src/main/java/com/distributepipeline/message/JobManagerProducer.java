package com.distributepipeline.message;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributepipeline.domain.Trigger;
import com.distributepipeline.domain.WorkFlow;

@Service
public class JobManagerProducer {


	
	@Autowired
	JobManagerConsumer jobManagerConsumer;
	
	@Autowired
	private KafkaTemplate<String, Trigger> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate1;
	
	
	
	@Autowired
	WorkFlow workFlow;
	
	@Autowired
	Trigger trigger;
	
    //method to send total no. of tasks
	
	public void sendNumberOfTasks(Integer noOfTasks) {
		kafkaTemplate1.send("socket-number", noOfTasks.toString());
	}
	
	//method to send workflow name to persistence manager
	public void sendWorkFlowName(String workFlowName) {
		    kafkaTemplate1.send("workFlowName", workFlowName);
			System.out.println("workflowname sent to persistence manager" );   
		}

	//method to send tasknames to socket
	public void sendTaskName(String taskName) {
	    kafkaTemplate1.send("socket-taskName", taskName);  
	}
	
 
	//method to send workflow to engine
	
	public void triggerEngine(WorkFlow workFlow) {

		//generating a unique id for each workflow
		UUID uuid = UUID.randomUUID();
		String jobId= uuid.toString();
		
		trigger.setJobId(jobId);
		trigger.setWorkflow(workFlow);
	    kafkaTemplate.send("trigger-engine", trigger);
		System.out.println("engine triggered by job manager");	   
	}
}
