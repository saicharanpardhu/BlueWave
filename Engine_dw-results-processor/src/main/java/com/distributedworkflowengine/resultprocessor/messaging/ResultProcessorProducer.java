package com.distributedworkflowengine.resultprocessor.messaging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.resultprocessor.domain.Model;
import com.distributedworkflowengine.resultprocessor.domain.ModelSend;
import com.distributedworkflowengine.resultprocessor.domain.User;
import com.distributedworkflowengine.resultprocessor.domain.UserJob;

import org.springframework.kafka.core.KafkaTemplate;


@Service
public class ResultProcessorProducer {
	private static Logger logger=LogManager.getLogger("ResultProcessorProducer.class");

	@Autowired
	ResultProcessorConsumer consumer;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private UserJob userJob;
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate2;
	@Autowired
	private KafkaTemplate<String, UserJob> kafkaTemplate3;
	
	 
	//method to send job id to job scheduler
	@Value("${kafka.topic.resulttojob}")
	private String resultToJob;
	public void sendMessage(Model model) {
		
		logger.info("sending to job");
		userJob.setJobId(model.getJobId());
		userJob.setUserName(model.getUserName());
		kafkaTemplate3.send(resultToJob, userJob);
		
	}
	
	//method to send results to api-gateway
	@Value("${kafka.topic.messagetoapi}")
	private String messageToApi;
	public void sendMessageApi() {
		
		logger.info("response to gateway");
		kafkaTemplate.send(messageToApi, "task-completed");
		
	}
	
	//method to send status to socket
	@Value("${kakfa.topic.messagetosocket}")
	private String messageToSocket;
	public void sendMessageSocket(User user) {
		
		logger.info("sending to socket");
		kafkaTemplate2.send(messageToSocket,user);
		
	}
}