package com.sr.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import com.sr.domain.*;
@Service
public class ReportingServiceProducer {
	@Autowired
	private KafkaTemplate<String, Report> kafkaTemplate;
	 
	public void sendMessage(Report report) {
	    kafkaTemplate.send("testrun4", report);
	   
	}
}
