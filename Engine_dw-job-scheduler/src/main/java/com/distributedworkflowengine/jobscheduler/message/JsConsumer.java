package com.distributedworkflowengine.jobscheduler.message;


import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	ReportModel reportModel;
	
	@Autowired
	TaskToScheduler taskToScheduler;

	@Autowired
	JsProducer producer;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

    //method to consume unique job id from state initializer
	
	 
	@KafkaListener(topics = "${kafka.topic.bootnew}",
			containerFactory="UserKafkaListenerContainerFactory")

	public void reportlistener(User user) throws JsonProcessingException {
		
		reportModel.setJobStartTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(user.getJobId());
		jobInfo=service.getData(user.getJobId());   
		Map<String,Task> map=service.selectJob(jobInfo,user.getJobId(),user.getUserName());
		
		if(!map.isEmpty()) {
			taskToScheduler.setJobId(user.getJobId());
			taskToScheduler.setListOfTasks(map);
			taskToScheduler.setWorkFlowName(jobInfo.getWorkFlowName());
			System.out.println(user.getUserName());
			taskToScheduler.setUserName(user.getUserName());
			producer.sendToTaskSche(taskToScheduler);
			latch.countDown();
		}
		else
			{
			
				System.out.println("Job completed");
			}




	}

}
