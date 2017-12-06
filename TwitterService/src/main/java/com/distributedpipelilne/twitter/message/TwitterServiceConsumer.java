package com.distributedpipelilne.twitter.message;


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

import com.distributedpipelilne.twitter.domain.ConsoleOutput;
import com.distributedpipelilne.twitter.domain.Input;
import com.distributedpipelilne.twitter.domain.Output;
import com.distributedpipelilne.twitter.domain.ReportModel;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


@Service
public class TwitterServiceConsumer {
	
    
    private static Logger logger = LogManager.getLogger(TwitterServiceConsumer.class);
	
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
	 TwitterServiceProducer producer;
	 
	 @Autowired
	 ReportModel reportModel;
	 
	 																																																																																																																																																																																																
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	/*
	 * consumer on kafka topic <add the kafak topic>"
	 */
	@KafkaListener(topics = "twitter3", 
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
		producer.sendReport(reportModel);
		
		try{
			
		
			logger.info("Task Processing...");
			/*
			 * Write the command that should be executed <command>
 			 */
			Twitter twitter = TwitterFactory.getSingleton();
			Status status = twitter.updateStatus("Twitter update from Bluewave: " + inputdata.getInput()[0]);
		    
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
