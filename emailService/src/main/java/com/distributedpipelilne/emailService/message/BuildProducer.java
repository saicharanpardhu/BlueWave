package com.distributedpipelilne.emailService.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.emailService.domain.ConsoleOutput;
import com.distributedpipelilne.emailService.domain.Input;
import com.distributedpipelilne.emailService.domain.Output;
import com.distributedpipelilne.emailService.domain.ReportModel;



@Service
public class BuildProducer {
	
    

	
	@Autowired
	BuildConsumer buildConsumer;
	
	
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
