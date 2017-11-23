package com.distributedworkflowengine.stateinit.messenger;
 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.ReportModel;
import com.distributedworkflowengine.stateinit.domain.Task;
import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.distributedworkflowengine.stateinit.domain.User;
import com.distributedworkflowengine.stateinit.domain.WorkFlow;
import com.distributedworkflowengine.stateinit.services.RedisoprImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class StateInitializerConsumer {
	
	@Autowired
	StateInitializerProducer engine;
	@Autowired
	User user;
	@Autowired
	RedisoprImpl redisoprImpl;
	
	@Autowired
	ReportModel reportModel;
	
	@Autowired
	WorkFlow workFlow;
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	  //<!-- method to get jobid and workflow object from job manager -->
	  
	@KafkaListener(topics = "${kafka.topic.triggerengine}", 
			  containerFactory = "triggerKafkaListenerContainerFactory")
		public void triggerlistener(Trigger trigger) throws JsonProcessingException {
		
		System.out.println(trigger.getJobId());
		System.out.println(trigger.getWorkFlow());
		System.out.println("sending to save");
		
		redisoprImpl.saveRedis(trigger);
		user.setJobId(trigger.getJobId());
		user.setUserName(trigger.getUserName());
		engine.sendMessage(user);
		reportModel.setJobStartTime(new Timestamp(System.currentTimeMillis()));
		reportModel.setJobId(trigger.getJobId());
		
		Map<String,Task> mp=new HashMap<String,Task>();
		mp=trigger.getWorkFlow().getTasks();
		Set<String > keys=mp.keySet();
		for(String key:keys)
		{
			reportModel.setWorkFlowName(trigger.getWorkFlow().getWorkFlowName());
			reportModel.setTaskAlias(key);
			
			engine.sendReport(reportModel);
		}

			    latch.countDown();
			} 
}