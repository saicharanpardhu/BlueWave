package com.distributepipeline.task.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributepipeline.task.domain.ConsoleOutput;
import com.distributepipeline.task.domain.OutputModel;
import com.distributepipeline.task.domain.ReportModel;


@Service
public class RunAgentProducer {
	
	@Autowired
	RunAgentConsumer runAgentConsumer;
	
	
	@Autowired
	private KafkaTemplate<String, OutputModel> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, ConsoleOutput> kafkaTemplate2;
	
	
	@Autowired
	private KafkaTemplate<String, ReportModel> kafkaTemplate3;
	
	@Autowired
	OutputModel output;
	
	
	 
	public void sendMessage() {
		System.out.println("Producer console");
    kafkaTemplate.send("git3", output);
	   
	}
	
	public void sendMessageConsole(ConsoleOutput consoleOutput) {
		System.out.println("Producer console");
		System.out.println(consoleOutput.getConsole());
		System.out.println(consoleOutput.getUserName());
    kafkaTemplate2.send("console", consoleOutput);
	   
	}
	public void sendReport(ReportModel reportModel) {
		
    kafkaTemplate3.send("reports", reportModel);
	   
	}
	
}