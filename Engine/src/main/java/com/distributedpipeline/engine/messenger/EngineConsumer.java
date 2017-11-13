package com.distributedpipeline.engine.messenger;
 
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.distributedpipeline.engine.domain.TaskResponse;
import com.distributedpipeline.engine.domain.Trigger;
import com.distributedpipeline.engine.services.JobScheduler;
import com.distributedpipeline.engine.services.ResultProcessor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

//<!----Consumer: consumes String and Report -->
@Service
public class EngineConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	  private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	@KafkaListener(topics = "trigger-engine", 
			  containerFactory = "triggerKafkaListenerContainerFactory")
			public void triggerlistener(Trigger trigger) {
				JobScheduler.addJobToPipeline(trigger);
			    latch.countDown();
			} 
	
	@KafkaListener(topics = "task-response", 
			  containerFactory = "taskResponseKafkaListenerContainerFactory")
			public void taskReponselistener(TaskResponse taskResponse) {
				ResultProcessor.processResult(taskResponse);
			    latch.countDown();
			} 
	 
}