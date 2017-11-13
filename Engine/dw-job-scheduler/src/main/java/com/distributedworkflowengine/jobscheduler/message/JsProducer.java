package com.distributedworkflowengine.jobscheduler.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
@Service
public class JsProducer {
	
//	 @Autowired
//	    private KafkaTemplate<String, JobInfo> kafkaTemplate;
//	    
	 @Autowired
	    private KafkaTemplate<String, TaskToScheduler> kafkaTemplate;
	    
//	    @Autowired
//	    private KafkaTemplate<String, Integer> kafkaTemplate2;
	     
//	    public void sendMessage(JobInfo model) {
//	        kafkaTemplate.send("JobStatus4", model);
//	       
//	    }
	    
	    public void	 sendMessage2(TaskToScheduler model) {
	    	//System.out.println("pro");
	        kafkaTemplate.send("JobScheToTaskSched2", model);
	        System.out.println("Data sent to Task Scheduler");
	       
	    }

	}