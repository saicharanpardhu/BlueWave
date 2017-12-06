package com.distributedpipelilne.testAgent.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.testAgent.domain.ConsoleOutput;
import com.distributedpipelilne.testAgent.domain.Input;
import com.distributedpipelilne.testAgent.domain.Output;
import com.distributedpipelilne.testAgent.domain.ReportModel;

/*
 * this is the Consumer class for kafka which is used to test a maven project
 */

@Service
public class testAgentConsumer {
	
    private static Logger logger = LogManager.getLogger("MyApp.class");

	
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
	 testAgentProducer producer;
	 
//	 @Autowired
//	 ReportModel reportModel;
	 
	
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	/*
	 * consumer on kafka topic "test2"
	 */

	@KafkaListener(topics = "test5", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
		ReportModel  reportModel = new ReportModel();

		/*
		 * getting the file name through the Git url and also the name of the test file
		 */
		reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
		reportModel.setTaskEndTime(null);
		System.out.println(reportModel.getTaskStartTime()+"******");
		String[] url = inputdata.getInput();
		
		String fname2[] = url[0].split("/");
		String fileName = fname2[fname2.length-1].split("\\.")[0];
        logger.info(fileName);
        
		String path = "/Task_Source";
		StringBuffer output1 = new StringBuffer();
		File dir = new File(path);	
		String filePath = path+"/"+fileName;
		//File filepath = new File(filePath);
		File filepath = new File("/usr/src/");
		/*
		 * setting starting time stamp
		 */
		try{
			logger.info("./Test-Plugin.sh "+filePath);
			logger.info("Task Processing...");
			
			 logger.info("mvn -Dtest="+url[1]+" test");
			 /*
				 * executing the "mvn -Dtest="< test file name> test" for the Build-Plugin.sh
	 			 */

			Process process = Runtime.getRuntime().exec("./Test-Plugin1.sh "+url[1]+" test"+" "+fileName,null, filepath);
			// Process process = Runtime.getRuntime().exec("mvn -Dtest="+url[1]+" test",null,filepath);
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

	        //PrintWriter writer = new PrintWriter("/home/avalabche/Desktop/output.txt", "UTF-8");
	         consoleOutput.setTaskName(inputdata.getTaskname());
             consoleOutput.setUserName(inputdata.getUserName());
             /*
              * sending the data to UI 
              */
             consoleOutput.setConsole(output1.toString());	
             logger.info(output1.toString());
	         //writer.println(output1);
	         
	         //writer.close();
	         
	         logger.info(inputdata.getUserName());
	         

			if(process.exitValue()==0) {
				String []str= {"Task Complete"};
				output.setOutput(str);
				output.setErrcode(200);
				output.setStderr("task complete");
				logger.info("maroti task complete");
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
		reportModel.setTaskEndTime(new Date(System.currentTimeMillis()));
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
		
		/*
		 * calling the kafka producer 
		 */
		producer.sendMessage();
		producer.sendReport(reportModel);
		producer.sendMessageConsole(consoleOutput);
		
	}

}
