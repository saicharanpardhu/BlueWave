package com.distributepipeline.task.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import com.distributepipeline.task.model.Output;
import com.distributepipeline.task.model.ReportModel;



@Service
public class DemoProducer {

	@Autowired
	DemoConsumer demoConsumer;
	
	@Autowired
	private KafkaTemplate<String, Output> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, ReportModel> kafkaTemplateReport;
	
	@Autowired
	Output output;
	
	@Autowired 
	ReportModel reportModel;
	 
	public void sendMessage(Output output) {
		
		
	    kafkaTemplate.send("git3", output);
	   
	}
	
	public void sendReport(ReportModel reportModel) {
		 kafkaTemplateReport.send("reports", reportModel);
		 System.out.println("reports send to reporting manager");
		 System.out.println("start time:"+ reportModel.getTaskStartTime());
		 System.out.println("end time:"+ reportModel.getTaskEndTime());
		
	}
	
}
