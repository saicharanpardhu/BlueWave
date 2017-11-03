package com.distributedpipeline.engine.messenger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.distributedpipeline.engine.domain.Report;
import com.distributedpipeline.engine.domain.Trigger;

import org.springframework.kafka.core.KafkaTemplate;
//<!----Reporting Service Producer: Produces Report and String-->
@Service
public class EngineProducer {
	@Autowired
	private KafkaTemplate<String, Report> kafkaTemplate;
	  
	 
	public void sendMessage(Report report) {
	    kafkaTemplate.send("report-topic", report);  
	} 
}
