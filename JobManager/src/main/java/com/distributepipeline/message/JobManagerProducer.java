package com.distributepipeline.message;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${kafka.topic.workflowfrompersistence}")
	private String topic1;
	
	//method to send workflow name to persistence manager
	public void sendWorkFlowName(String workFlowName) {
		    kafkaTemplate1.send("workFlowName1", workFlowName);
			System.out.println("workflowname sent to persistence manager" );   
		}
	
	@Value("${kafka.topic.tasknametosocket}")
	private String topic2;
	

	//method to send tasknames to socket
	public void sendTaskName(ModelToSocket modelToSocket) {
		System.out.println("taskname "+ modelToSocket.getTaskName());
		System.out.println("username "+ modelToSocket.getUserName());
	    kafkaTemplateToSocket.send(topic2, modelToSocket);  
	}
	
 
	@Value("${kafka.topic.triggerengine}")
	private String topic3;
	
	//method to send workflow to engine
	
	public void triggerEngine(WorkFlow workFlow) {

		//generating a unique id for each workflow
		UUID uuid = UUID.randomUUID();
		 
		this.jobId=uuid.toString();
		

		trigger.setUserName(jobManagerController.getUserName());
		trigger.setJobId(jobId);
		trigger.setWorkflow(workFlow);
	    kafkaTemplate.send(topic3, trigger);
		System.out.println("engine triggered by job manager");	   
	}
	
	@Value("${kafka.topic.detailstopersistence}")
	private String topic4;
	
	public void jobIdDetailsToPersistence(String userName,String workFlowName) {
        jobIdDetails.setJobId(trigger.getJobId());
        System.out.println("jdslkjlkf:"+jobIdDetails.getJobId());
		jobIdDetails.setUserName(userName);
		jobIdDetails.setWorkFlowName(workFlowName);
		jobIdToSocket(jobIdDetails);
		kafkaTemplate2.send(topic4,jobIdDetails);	
					
	}
	
	@Value("${kafka.topic.jobIdDetailsToSocket}")
	private String topic5;
	
	public void jobIdToSocket(JobIdDetails jobIdDetails) {
	   kafkaTemplate2.send(topic5,jobIdDetails);	
	}
	
}
