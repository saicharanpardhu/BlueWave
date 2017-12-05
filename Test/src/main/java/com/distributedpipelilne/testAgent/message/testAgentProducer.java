package com.distributedpipelilne.testAgent.message;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.testAgent.domain.ConsoleOutput;
import com.distributedpipelilne.testAgent.domain.Input;
import com.distributedpipelilne.testAgent.domain.Output;
import com.distributedpipelilne.testAgent.domain.ReportModel;



@Service
public class testAgentProducer {
    private static Logger logger = LogManager.getLogger("MyApp.class");

	
	@Autowired
	testAgentConsumer buildConsumer;
	
	
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
    	logger.info(consoleOutput.getUserName());

	    kafkaTemplate2.send("console", consoleOutput);
	   
	}

	public void sendReport(ReportModel reportModel) {
    kafkaTemplateReport.send("reports", reportModel);
   
	}

}
