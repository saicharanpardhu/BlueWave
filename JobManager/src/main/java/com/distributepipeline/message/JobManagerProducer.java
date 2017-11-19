package com.distributepipeline.message;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributepipeline.controller.JobManagerController;
import com.distributepipeline.domain.JobIdDetails;
import com.distributepipeline.domain.ModelToSocket;
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
	private KafkaTemplate<String, ModelToSocket> kafkaTemplateToSocket;
	
	@Autowired
	private KafkaTemplate<String, JobIdDetails> kafkaTemplate2;

	
	private String jobId;
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@Autowired
	WorkFlow workFlow;
	
	@Autowired
	Trigger trigger;
	
	@Autowired
	JobIdDetails jobIdDetails;
	
	@Autowired
	JobManagerController jobManagerController;
	
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
	public void sendTaskName(ModelToSocket modelToSocket) {
		System.out.println("taskname "+ modelToSocket.getTaskName());
		System.out.println("username "+ modelToSocket.getUserName());
	    kafkaTemplateToSocket.send("socket-taskName", modelToSocket);  
	}
	
 
	//method to send workflow to engine
	
	public void triggerEngine(WorkFlow workFlow) {

		//generating a unique id for each workflow
		UUID uuid = UUID.randomUUID();
		 
		this.jobId=uuid.toString();
		trigger.setUserName(jobManagerController.getUserName());
		trigger.setJobId(jobId);
		trigger.setWorkflow(workFlow);
	    kafkaTemplate.send("trigger-engine", trigger);
		System.out.println("engine triggered by job manager");	   
	}
	
	public void jobIdDetailsToPersistence(String userName,String workFlowName) {
        
		jobIdDetails.setJobId(this.jobId);
		jobIdDetails.setUserName(userName);
		jobIdDetails.setWorkFlowName(workFlowName);
		kafkaTemplate2.send("jobIdDetailsToPersistence",jobIdDetails);		
		
	}

	
}
