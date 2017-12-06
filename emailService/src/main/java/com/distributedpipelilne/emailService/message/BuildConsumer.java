package com.distributedpipelilne.emailService.message;


import static org.mockito.Matchers.contains;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.emailService.domain.ConsoleOutput;
import com.distributedpipelilne.emailService.domain.Input;
import com.distributedpipelilne.emailService.domain.Output;
import com.distributedpipelilne.emailService.domain.ReportModel;
import com.distributedpipelilne.emailService.service.MailingServiceImpl;

@Service
public class BuildConsumer {
	
	@Autowired
	MailingServiceImpl service;
    private static Logger logger = LogManager.getLogger(BuildConsumer.class);
	
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 @Autowired 
	 private Input input;
	  @Autowired
	  ConsoleOutput consoleOutput;
	 
	 @Autowired
	 Output output;
	 
	 @Autowired
	 BuildProducer producer;
	 
	 @Autowired
	 ReportModel reportModel;
	 
	 																																																																																																																																																																																																
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	/*
	 * consumer on kafka topic <add the kafak topic>"
	 */
	@KafkaListener(topics = "emails2", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
		/*
		 * declare a variable for the Input array from Input model
		 */
		StringBuffer output1 = new StringBuffer();
		
		/*
		 * starting time stamp
		 */
		reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
		
		
		/*
		 * sending data to reporting model 
		 */

		reportModel.setTaskAlias(inputdata.getTaskname());
		reportModel.setJobId(inputdata.getJobId());
		reportModel.setTaskEndTime(null);
		producer.sendReport(reportModel);
		
		try{
			
		
			logger.info("Task Processing...");
			/*
			 * Write the command that should be executed <command>
 			 */
			
	          String email = inputdata.getInput()[0];
//	          service.put(email,"You job has been successfully executed");
	          service.put(email,"You jobId:"+inputdata.getJobId()+" has been successfully executed. This is a system generated mail. Do not reply");
	          System.out.println(email);
			
				String []str= {"Task Complete"};
				output.setOutput(str);
				output.setErrcode(200);
				output.setStderr("task complete");
				logger.info("task complete");
				reportModel.setJobStatus("build complete");
			
		}
		catch (Exception e){
			logger.error("Task failed");
			output.setErrcode(400);
			output.setStderr("Task failed");
		}
		/*
		 * sending the data to reporting service 
		 */
		
		reportModel.setTaskAlias(inputdata.getTaskname());
		reportModel.setTaskLogs(output1.toString());
		reportModel.setJobId(inputdata.getJobId());
		
		/*
		 * sending the data to Result-processor
		 */
		output.setUserName(inputdata.getUserName());
		output.setJobId(inputdata.getJobId());
		output.setTaskName(inputdata.getTaskname());
		output.setType(inputdata.getType());
		reportModel.setTaskEndTime(new Date(System.currentTimeMillis()));
		System.out.println(reportModel.getTaskEndTime()+"#####");
																																																																																																																																																																																																					
		
		/*
		 * calling the kafka producer 
		 */
		
		producer.sendMessage();
		producer.sendReport(reportModel);
		producer.sendMessageConsole(consoleOutput);
		}

}
