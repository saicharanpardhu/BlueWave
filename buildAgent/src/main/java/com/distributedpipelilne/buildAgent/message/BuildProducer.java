package com.distributedpipelilne.buildAgent.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.buildAgent.domain.ConsoleOutput;
import com.distributedpipelilne.buildAgent.domain.Input;
import com.distributedpipelilne.buildAgent.domain.Output;
import com.distributedpipelilne.buildAgent.domain.ReportModel;



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
	
	
	
	

	
	 
	public void sendMessage() {
		
	    kafkaTemplate.send("git3", output);
	   
	}
public void sendMessageConsole(ConsoleOutput consoleOutput) {
	    //System.out.println(consoleOutput.getConsole());
    	System.out.println(consoleOutput.getUserName());

	    kafkaTemplate2.send("console", consoleOutput);
	   
	}

public void sendReport(ReportModel reportModel) {
    kafkaTemplateReport.send("reports", reportModel);
   
}

}
