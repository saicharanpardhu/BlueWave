package com.distributedpipelilne.buildAgent.message;


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

import com.distributedpipelilne.buildAgent.domain.Output;
import com.distributedpipelilne.buildAgent.domain.ReportModel;
import com.distributedpipelilne.buildAgent.domain.ConsoleOutput;
import com.distributedpipelilne.buildAgent.domain.Input;

/*
 * this is the Consumer class for kafka which is used to build a maven project using script file
 */
@Service
public class BuildConsumer {
	
    
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
	 * consumer on kafka topic "Build1"
	 */
	@KafkaListener(topics = "Build1", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
		
		/*
		 * getting the file name through the Git url 
		 */
		reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
		System.out.println(reportModel.getTaskStartTime()+"******");
		String[] url = inputdata.getInput();
		String fname[] = url[0].split("/");
		String fileName = fname[fname.length-1].split("\\.")[0];
        System.out.println(fileName);
		String path = "/home/avalabche/testrun2/"+inputdata.getJobId()+"/";
		StringBuffer output1 = new StringBuffer();
		File dir = new File(path);	
		String filePath = path+"/"+fileName; 
		/*
		 * path where the script file is located
		 */
		File directory = new File("/home/avalabche/Desktop/final_clone_2.0/buildAgent");
		
		/*
		 * setting starting time stamp
		 */
		
		reportModel.setTaskAlias(inputdata.getTaskname());
		reportModel.setJobId(inputdata.getJobId());
		producer.sendReport(reportModel);
		
		try{
			System.out.println("sout");
			logger.info("./Build-Plugin.sh "+filePath);
			logger.info("Task Processing...");
			/*
			 * executing the "mvn clean package" for the Build-Plugin.sh
 			 */
			Process process = Runtime.getRuntime().exec("./Build-Plugin.sh "+filePath,null,directory);
			process.waitFor();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			 String line = "";
			 while ((line = reader.readLine()) != null)
			 {
				 logger.info(line);
			    output1.append(line + "\n");
			    
			 }
			 logger.info("task complete");
			 
			 /*
			  * writing the console output in the output.txt
			  */
			 
	         PrintWriter writer = new PrintWriter("/home/avalabche/Desktop/output.txt", "UTF-8");
	         consoleOutput.setTaskName(inputdata.getTaskname());
             consoleOutput.setUserName(inputdata.getUserName());
             /*
              * sending the data to UI 
              */
             consoleOutput.setConsole(output1.toString());	
	         writer.println(output1);
	         
	         writer.close();
	         
	         logger.info(inputdata.getUserName()); 
			if(process.exitValue()==0) {
				String []str= {"Task Complete"};
				output.setOutput(str);
				output.setErrcode(200);
				output.setStderr("task complete");
				logger.info("task complete");
				reportModel.setJobStatus("build complete");
			}
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
//		Timestamp endtime = new Timestamp(System.currentTimeMillis());
		System.out.println(reportModel.getTaskEndTime()+"#####");
																																																																																																																																																																																																					
		
		/*
		 * calling the kafka producer 
		 */
		
		producer.sendMessage();
		producer.sendReport(reportModel);
		producer.sendMessageConsole(consoleOutput);
		}

}
