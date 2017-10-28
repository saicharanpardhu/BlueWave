package com.distributedpipeline.reporting.messenger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.distributedpipeline.reporting.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
//<!----Reporting Service Producer: Produces Report and String-->
@Service
public class ReportingServiceProducer {
	@Autowired
	private KafkaTemplate<String, Report> kafkaTemplate;
	 
	@Autowired
	private KafkaTemplate<String, String> kafkaStringTemplate;
	 
	public void sendMessage(Report report) {
	    kafkaTemplate.send("testrun4", report);
	   
	}
	public void send(String message) {
		kafkaStringTemplate.send("testrun4", message); 
	}
}
