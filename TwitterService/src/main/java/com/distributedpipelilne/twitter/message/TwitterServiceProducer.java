package com.distributedpipelilne.twitter.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.twitter.domain.ConsoleOutput;
import com.distributedpipelilne.twitter.domain.Input;
import com.distributedpipelilne.twitter.domain.Output;
import com.distributedpipelilne.twitter.domain.ReportModel;



@Service
public class TwitterServiceProducer {
	
    

	
	@Autowired
	TwitterServiceConsumer buildConsumer;
	
	
	@Autowired
	private KafkaTemplate<String, Output> kafkaTemplate;
	
	@Autowired
	Input input;
	
	@Autowired
	Output output;
	
	@Autowired
	ReportModel reportModel;
	
	@Autowired
	private KafkaTemplate<String, ConsoleOutput> kafkaTemplate2;
	
	@Autowired
	private KafkaTemplate<String, ReportModel> kafkaTemplateReport;
	
	
	
	

	
	//method to send data to Result-processor
	public void sendMessage() {
	    kafkaTemplate.send("git3", output); 
	}
	//method to send data to User with the console output
	public void sendMessageConsole(ConsoleOutput consoleOutput) {
//	    	System.out.println(consoleOutput.getUserName());
	    	kafkaTemplate2.send("console", consoleOutput);  
		}
	//method to send data to reporting service 
	public void sendReport(ReportModel reportModel) {
	    kafkaTemplateReport.send("reports", reportModel);  
	}

}
