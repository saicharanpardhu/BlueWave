package com.distributedworkflowengine.stateinit.messenger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.ReportModel;
import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.distributedworkflowengine.stateinit.domain.User;

import org.springframework.kafka.core.KafkaTemplate;
//<!----Reporting Service Producer: Produces Report and String-->
@Service
public class StateInitializerProducer {
	private static Logger logger=LogManager.getLogger("StateInitializerProducer.class");

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	  
	@Autowired
	private KafkaTemplate<String, ReportModel> kafkaTemplate2;
	
	//<!-- method to send jobId to job scheduler -->
	 @Value("${kafka.topic.statetojob}")
	 private String stateToJob;
	public void sendMessage(User user) {

	    kafkaTemplate.send(stateToJob,user );  
	} 
	
	 @Value("${kafka.topic.reportStatus}")
	 private String jobStatus;
	public void sendReport(ReportModel model) {

	    kafkaTemplate2.send(jobStatus,model );  
	} 
	
	
}
