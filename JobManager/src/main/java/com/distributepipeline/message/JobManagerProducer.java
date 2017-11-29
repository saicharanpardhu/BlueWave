package com.distributepipeline.message;

import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

	private static Logger logger = LogManager.getLogger("JobManagerProducer.class");

	
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
		String userName=jobManagerController.getUserName();
		String string1=noOfTasks.toString();
		userName=userName.concat("-");
		userName=userName.concat(string1);
		logger.info(userName);
		kafkaTemplate1.send("socket-number", userName);
	}
	
	@Value("${kafka.topic.workflowfrompersistence}")
	private String topic1;
	
	//method to send workflow name to persistence manager
	public void sendWorkFlowName(String workFlowName) {
		    kafkaTemplate1.send("workFlowName1", jobManagerController.getUserName()+"-"+workFlowName);
			logger.info("workflowname sent to persistence manager" );   
		}
	
	@Value("${kafka.topic.tasknametosocket}")
	private String topic2;
	

	//method to send tasknames to socket
	public void sendTaskName(ModelToSocket modelToSocket) {
		modelToSocket.setUserName(jobManagerController.getUserName());
		logger.info("taskname "+ modelToSocket.getTaskName());
		logger.info("username "+ modelToSocket.getUserName());
	    kafkaTemplateToSocket.send(topic2, modelToSocket);  
	}
	
 
	@Value("${kafka.topic.triggerengine}")
	private String topic3;
	@Value("${kafka.topic.jobIdToSocket}")
	private String topic4;
	
	//method to send workflow to engine
	
	public void triggerEngine(WorkFlow workFlow) {

		//generating a unique id for each workflow
		UUID uuid = UUID.randomUUID();
		this.jobId=uuid.toString();

		trigger.setUserName(jobManagerController.getUserName());
		trigger.setJobId(uuid.toString());
		logger.info(uuid.toString());
		trigger.setWorkflow(workFlow);
		String toSocket=jobManagerController.getUserName() + " "+jobId;
		
		jobIdDetails.setJobId(this.jobId);
		jobIdDetails.setUserName(jobManagerController.getUserName());
		jobIdDetails.setWorkFlowName(workFlow.getWorkFlowName());
		jobIdToSocket(jobIdDetails);
		jobIdDetailsToPersistence(jobManagerController.getUserName(),workFlow.getWorkFlowName());
		System.out.println("lkdsfhlkjdfsh::"+ jobIdDetails.getJobId());
		
		
		kafkaTemplate1.send("topic4", toSocket);
		logger.info(toSocket);
		logger.info("jobid sent to socket");
	    kafkaTemplate.send(topic3, trigger);
		logger.info("engine triggered by job manager");	   
	}
	
	@Value("${kafka.topic.detailstopersistence}")
	private String topic5;
	
	
	//method to send jobid details to persistence
	public void jobIdDetailsToPersistence(String userName,String workFlowName) {
        
        logger.info("jdslkjlkf:"+trigger.getJobId());
        jobIdDetails.setUserName(userName);
		jobIdDetails.setWorkFlowName(workFlowName);
		countStatusToPersistence(jobIdDetails.getUserName(), workFlowName);
		logger.info("jobid send:"+jobIdDetails.getJobId());
		kafkaTemplate2.send(topic5,jobIdDetails);	
					
	}
	
	//method to send jobid details to socket
	@Value("${kafka.topic.jobIdDetailsToSocket}")
	private String topic6;
	
	public void jobIdToSocket(JobIdDetails jobIdDetails) {
	   kafkaTemplate2.send(topic6,jobIdDetails);	
	}
	
	@Value("${kafka.topic.countStatus}")
	private String topic7;
	
	public void countStatusToPersistence(String userName,String workFlowName) {
		
		Date time=(new Timestamp(System.currentTimeMillis()));
		 kafkaTemplate1.send(topic7,userName+"&"+workFlowName+"&"+time);	
		 logger.info(userName+"&"+workFlowName+"&"+time);
	}
		

}
