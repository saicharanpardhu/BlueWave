package com.distributedworkflowengine.stateinit.messenger;
 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.Task;
import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.distributedworkflowengine.stateinit.services.JobScheduler;
import com.distributedworkflowengine.stateinit.services.RedisoprImpl;
import com.distributedworkflowengine.stateinit.services.ResultProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class EngineConsumer {
	
	@Autowired
	EngineProducer engine;
	
	@Autowired
	RedisoprImpl redisoprImpl;
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	@KafkaListener(topics = "trigger-engine", 
			  containerFactory = "triggerKafkaListenerContainerFactory")
		public void triggerlistener(Trigger trigger) throws JsonProcessingException {
		
		System.out.println(trigger.getJobId());
		System.out.println(trigger.getWorkFlow());

//		System.out.println(trigger.getWorkFlow().getTasks().get("upperCase").getStatus());

		System.out.println("sending to save");
		
		redisoprImpl.saveRedis(trigger);
//				JobScheduler.addJobToPipeline(trigger);

		engine.sendMessage(trigger.getJobId());

			    latch.countDown();
			} 
//	
//	@KafkaListener(topics = "task-response", 
//			  containerFactory = "taskResponseKafkaListenerContainerFactory")
//			public void taskReponselistener(TaskResponse taskResponse) {
//				ResultProcessor.processResult(taskResponse);
//			    latch.countDown();
//			} 
//	@KafkaListener(topics = "task", 
//			  containerFactory = "taskKafkaListenerContainerFactory")
//			public void tasklistener(Task task) {
//				
//			    latch.countDown();
//			} 
//	@KafkaListener(topics = "workflow", 
//			  containerFactory = "workflowKafkaListenerContainerFactory")
//			public void tasklistener(Workflow workflow) {
//				
//			    latch.countDown();
//			} 
}