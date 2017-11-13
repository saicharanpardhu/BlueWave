package com.distributedworkflowengine.jobscheduler.message;


import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.*;
import com.distributedworkflowengine.jobscheduler.services.JobSchedulerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsConsumer {
	
	@Autowired
	JobSchedulerServiceImpl service;
	
	@Autowired
	WorkFlow jobInfo;
	
	@Autowired
	TaskToScheduler taskToScheduler;
	
	@Autowired
	JsProducer producer;
	
	private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
      return latch;
    }
  
  
  @KafkaListener(topics = "StateToJobSche",
		  containerFactory="kafkaListenerContainerFactory")
          public void reportlistener(String job) throws JsonProcessingException {
              System.out.println(job);
//              System.out.println("aaaaaaaaa");

	  System.out.println("jobId recieved");
      
	  jobInfo=service.getData(job);
              System.out.println("jobInfo recieved");
              System.out.println(jobInfo);
              
             Map<String,Task> map=service.selectJob(jobInfo,job);
             if(!map.isEmpty()) {
              taskToScheduler.setJobId(job);
              taskToScheduler.setListOfTasks(map);
              producer.sendMessage2(taskToScheduler);
              latch.countDown();
             }
             else
            	 System.out.println("Task completed");
            
//              System.out.println(taskToScheduler.getListOfTasks().get("clone").getStatus());
//              System.out.println(taskToScheduler);
              
//              ObjectMapper mapperObj = new ObjectMapper();
//              String jsonStr = mapperObj.writeValueAsString(taskToScheduler);
//              
//              System.out.println(jsonStr);
              
              
          }
//  @KafkaListener(topics = "StateToJobSche",
//		  containerFactory="kafkaListenerContainerFactory")
//          public void reportlistenerResult(String job) throws JsonProcessingException {
//	  		System.out.println(job+"dfdsfsf");
//              latch.countDown();
//          }

}
