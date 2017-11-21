package com.distributedworkflowengine.jobscheduler.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
@Service
public class JsProducer {
		    
	 @Autowired
	    private KafkaTemplate<String, TaskToScheduler> kafkaTemplate;
	    
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
	    

	}