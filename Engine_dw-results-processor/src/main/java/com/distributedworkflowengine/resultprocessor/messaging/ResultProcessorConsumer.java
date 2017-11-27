package com.distributedworkflowengine.resultprocessor.messaging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.resultprocessor.domain.Model;
import com.distributedworkflowengine.resultprocessor.domain.User;
import com.distributedworkflowengine.resultprocessor.domain.WorkFlow;
import com.distributedworkflowengine.resultprocessor.service.ResultService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.springframework.kafka.annotation.KafkaListener;

@Service
public class ResultProcessorConsumer {
	private static Logger logger=LogManager.getLogger("JsConsumer.class");

	@Autowired
	ResultProcessorProducer producer;
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	private Model model;
	
	@Autowired
	ResultService resultService;
	
	
	@Autowired
	private RedisTemplate<String , String> redisTemplate ;
	@Autowired
	User user;
	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}

	private String Id;
	
	/* <--------consuming task model containing task status from agent 
	calling method to send the output to job scheduler
	calling method to update redis ------> */
	
	@KafkaListener(topics = "${kafka.topic.resultprocessorconsumer}", 
			containerFactory = "engineKafkaListenerContainerFactory")
	public void reportlistener(Model model) throws IOException {
		
		logger.info(model);
		if(model.getOutput()!=null)
		user.setOutput(model.getOutput()[0]);
		user.setUserName(model.getUserName());
		logger.info(user.getUserName());
		producer.sendMessageSocket(user);
		if(model.getErrcode()==200)
			resultService.updateRedis(model);
		else {
			String str=redisTemplate.opsForValue().get(model.getJobId());
			logger.info(str);
			Gson gson = new Gson();
			WorkFlow jobInfo = gson.fromJson(str, WorkFlow.class);
			jobInfo.getTasks().get(model.getTaskName()).setStatus("failed");
			ObjectMapper mapperObj2 = new ObjectMapper();
		     String jsonStrNew = mapperObj2.writeValueAsString(jobInfo);
		     
		     redisTemplate.opsForValue().set(model.getJobId(), jsonStrNew);
		}
		producer.sendMessage(model);
		producer.sendMessageApi();	
		latch.countDown();
		
	}
}