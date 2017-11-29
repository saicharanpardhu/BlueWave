package com.distributedpipelilne.agent.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.agent.model.Output;
import com.distributedpipelilne.agent.model.ReportModel;
import com.netflix.discovery.converters.Auto;


@Service
public class AgentProducer {
	
	@Autowired
	AgentConsumer agentConsumer;
	
	@Autowired
	private KafkaTemplate<String, Output> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, ReportModel> kafkaTemplateReport;
	
	@Autowired
	Output output;
	
	@Autowired
	ReportModel reportModel;
	
	
	 
	public void sendMessage(Output output) {
		
		System.out.println("idsent"+ output.getJobId());
		System.out.println(output.getTaskName());
//		output.setJobId("git");
		System.out.println("USERNAME   "+output.getUserName());
		
	    kafkaTemplate.send("git3", output);
	   System.out.println("? "+output.getJobId());
	   System.out.println("slkdkj" +output.getOutput()[0]);
	}
	
	public void sendReport(ReportModel reportModel) {
		 kafkaTemplateReport.send("reports", reportModel);
		
	}

}
