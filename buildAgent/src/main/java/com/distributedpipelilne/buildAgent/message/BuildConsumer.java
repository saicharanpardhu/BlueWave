package com.distributedpipelilne.buildAgent.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.buildAgent.domain.Output;
import com.distributedpipelilne.buildAgent.domain.ReportModel;
import com.distributedpipelilne.buildAgent.domain.ConsoleOutput;
import com.distributedpipelilne.buildAgent.domain.Input;


@Service
public class BuildConsumer {
	
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

	@KafkaListener(topics = "build", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
		//jlklsjadlkjf
		
		String fname2[] = inputdata.getInput().split("/");
     String fileName = fname2[fname2.length-1].split("\\.")[0];
        System.out.println(fileName);
		String path = "/home/avalabche/testrun2";
		StringBuffer output1 = new StringBuffer();
		File dir = new File(path);	
		String filePath = path+"/"+fileName;
		File directory = new File("/home/avalabche/Desktop/final_clone_2.0/buildAgent");
		reportModel.setTaskStartTime(new Timestamp(System.currentTimeMillis()));
		try{
			System.out.println("Task Processing...");
//			Process process = Runtime.getRuntime().exec("mvn clean package",null,directory);
			Process process = Runtime.getRuntime().exec("./Build-Plugin.sh "+filePath,null,directory);
			process.waitFor();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			 String line = "";
			 while ((line = reader.readLine()) != null)
			 {
			    System.out.println(line);
			    output1.append(line + "\n");
			    
			 }
			 System.out.println("task complete");

			
//	         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//	         String line = "";                       
//	         while ((line = reader.readLine())!= null) {
//
//	                 output1.append(line + "\n");
//	                
//	         }
	         PrintWriter writer = new PrintWriter("/home/avalabche/Desktop/output.txt", "UTF-8");
	         consoleOutput.setTaskName(inputdata.getTaskname());
             consoleOutput.setUserName(inputdata.getUserName());
             consoleOutput.setConsole(output1.toString());	
	         writer.println(output1);
	         
	         writer.close();
	         
	         System.out.println(inputdata.getUserName());
	         

//	         System.out.println(output1); 
			if(process.exitValue()==0) {
				String []str= {"Task Complete"};
				output.setOutput(str);
				output.setErrcode(200);
				output.setStderr("task complete");
				System.out.println("task complete");
				reportModel.setJobStatus("build complete");
			}
		}
		catch (Exception e){
			System.out.println("Task failed");
			output.setErrcode(400);
			output.setStderr("Task failed");
		}
		reportModel.setTaskEndTime(new Timestamp(System.currentTimeMillis()));
		output.setUserName(inputdata.getUserName());
		output.setJobId(inputdata.getJobId());
		output.setTaskName(inputdata.getTaskname());
		reportModel.setTaskAlias(inputdata.getTaskname());
		reportModel.setTaskLogs(output1.toString());
		reportModel.setJobId(inputdata.getJobId());
		output.setType(inputdata.getType());
		
		producer.sendMessage();
		producer.sendReport(reportModel);
		producer.sendMessageConsole(consoleOutput);
		
        
        //commented
//		
//				String fname="";
//				String path = "/home/avalabche/testrun2";
//				StringBuffer output1 = new StringBuffer();
//				File dir = new File(path);
//				File[] fList = dir.listFiles();
//				for(File file:fList) {
//				if (file.isDirectory()) {
//					fname = file.getName();
//				        }
//				}	
//				String filePath = path+"/"+fname;
////				File directory = new File(filePath);
//				File directory = new File("/home/avalabche/Desktop/final_clone_2.0/buildAgent");
//				reportModel.setTaskStartTime(new Timestamp(System.currentTimeMillis()));
//				try{
//					System.out.println("Task Processing...");
////					Process process = Runtime.getRuntime().exec("mvn clean package",null,directory);
//					Process process = Runtime.getRuntime().exec("./Build-Plugin.sh "+filePath,null,directory);
//					process.waitFor();
//					 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//					 String line = "";
//					 while ((line = reader.readLine()) != null)
//					 {
//					    System.out.println(line);
//					    output1.append(line + "\n");
//					    
//					 }
//					 System.out.println("task complete");
//
//					
////			         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////			         String line = "";                       
////			         while ((line = reader.readLine())!= null) {
////
////			                 output1.append(line + "\n");
////			                
////			         }
//			         PrintWriter writer = new PrintWriter("/home/avalabche/Desktop/output.txt", "UTF-8");
//			         consoleOutput.setTaskName(inputdata.getTaskname());
//	                 consoleOutput.setUserName(inputdata.getUserName());
//	                 consoleOutput.setConsole(output1.toString());	
//			         writer.println(output1);
//			         
//			         writer.close();
//			         
//			         System.out.println(inputdata.getUserName());
//			         
//
////			         System.out.println(output1); 
//					if(process.exitValue()==0) {
//						String []str= {"Task Complete"};
//						output.setOutput(str);
//						output.setErrcode(200);
//						output.setStderr("task complete");
//						System.out.println("task complete");
//						reportModel.setJobStatus("build complete");
//					}
//				}
//				catch (Exception e){
//					System.out.println("Task failed");
//					output.setErrcode(400);
//					output.setStderr("Task failed");
//				}
//				reportModel.setTaskEndTime(new Timestamp(System.currentTimeMillis()));
//				output.setUserName(inputdata.getUserName());
//				output.setJobId(inputdata.getJobId());
//				output.setTaskName(inputdata.getTaskname());
//				reportModel.setTaskAlias(inputdata.getTaskname());
//				reportModel.setTaskLogs(output1.toString());
//				reportModel.setJobId(inputdata.getJobId());
//				output.setType(inputdata.getType());
//				
//				producer.sendMessage();
//				producer.sendReport(reportModel);
//				producer.sendMessageConsole(consoleOutput);
					  //commented  
			}

}
