package com.distributedworkflowengine.jobscheduler.message;


import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.*;



import org.springframework.kafka.core.KafkaTemplate;
@Service
public class JsProducer {
	private static Logger logger=LogManager.getLogger("JsConsumer.class");

	@Autowired
	TaskToScheduler tasktosche;
		    
	 @Autowired
	    private KafkaTemplate<String, TaskToScheduler> kafkaTemplate;
	
	 @Autowired
		private KafkaTemplate<String, String> kafkaTemplate1;
	 
	 @Autowired
	    private KafkaTemplate<String, User> kafkaTemplate2;
	 
	 @Autowired
	    private KafkaTemplate<String, ReportModel> kafkaTemplate3;

	 //method to send task model to task scheduler
	 
	 @Value("${kafka.topic.jobschetotask}")
	 private String jobscheToTask;
	 
	 public void sendToTaskSche(TaskToScheduler model) {
	    	
	        kafkaTemplate.send(jobscheToTask, model);
	    }
	 
	    @Value("${kafka.topic.jobstatus}")
		 private String jobStatus;
	    public void sendToSocket(User user)
	    {
	    	kafkaTemplate2.send(jobStatus,user);
	    }

	    @Value("${kafka.topic.reportstatus}")
		 private String Status;
	    public void sendToReport(ReportModel model)
	    {
	    	kafkaTemplate3.send(Status,model);
	    }
	 
	    @Value("${kafka.topic.countStatus}")
			 private String countStatus;
	    public void sendToPersistence(String userName, Date jobEndTime)
	    {
	    
	    	kafkaTemplate1.send(countStatus,userName+"&"+tasktosche.getWorkFlowName()+"&"+jobEndTime);
	    }
	    

	}