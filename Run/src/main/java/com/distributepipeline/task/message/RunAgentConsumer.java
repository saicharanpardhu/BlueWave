package com.distributepipeline.task.message;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributepipeline.task.domain.ConsoleOutput;
import com.distributepipeline.task.domain.InputModel;
import com.distributepipeline.task.domain.OutputModel;
import com.distributepipeline.task.domain.ReportModel;

@Service
public class RunAgentConsumer {
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	

	  @Autowired
	  ReportModel reportModel;
	 
	 @Autowired
	 OutputModel output;
	 
	 @Autowired
	 ConsoleOutput consoleOutput;
	 
	 @Autowired
	 RunAgentProducer producer;
	 
	 @Autowired
	 InputModel input;
	
	 private String jobid;
	 private String taskname;


	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	@KafkaListener(topics = "run", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(InputModel  inputdata) throws IOException, InterruptedException {
		
				String fname="";					//"/GoPlacesBackend-0.1.0.jar";
				String fnameJar = "";
				
				String pathProject = "/home/jaydeep/Desktop/Task_source/"+inputdata.getJobId();// /Spring_restapi/target";
				
				System.out.println("p "+pathProject);


				
				String fname2[] = inputdata.getInput()[0].split("/");
			     String fileName = fname2[fname2.length-1].split("\\.")[0];
			     System.out.println(fileName);											//getting the fileName of .jar

				String pathJar = "/home/jaydeep/Desktop/Task_source/"+inputdata.getJobId()+"/"+fileName+"/target";
				System.out.println(pathJar);
				reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
				reportModel.setTaskAlias(inputdata.getTaskname());
				reportModel.setJobId(inputdata.getJobId());
				System.out.println("start time:"+ reportModel.getTaskStartTime());
				producer.sendReport(reportModel);     

				
				File dirJar = new File(pathJar);
				File[] fListJar = dirJar.listFiles();
				
				int fileFound = 0;
				
				for(File flist : fListJar) {
					fnameJar = flist.getName();
					System.out.println("sec "+fnameJar);
					if(fnameJar.contains(".jar") && !(fnameJar.contains(".jar.original")) )
						{							
						fileFound = 1;	
						break;
						}
				}
				
				System.out.println("FileFound "+fileFound);
				


				File directory = new File(pathJar+fnameJar);

				System.out.println("Task Running...");
				
				System.out.println("java -jar "+ pathJar+"/"+fnameJar);
				Process process = Runtime.getRuntime().exec("java -jar "+ pathJar+"/"+fnameJar);
				System.out.println(pathJar +" "+ fnameJar);


			if(process.isAlive() ) {
					String []str= {"Run Task Complete","https://172.23.238.186:8321/gitsearch/repo"};
					output.setOutput(str);
					output.setErrcode(200);
					output.setStderr("task complete");
					System.out.println("task complete!");
					
					
					reportModel.setTaskAlias(inputdata.getTaskname());
					reportModel.setJobId(inputdata.getJobId());
					reportModel.setTaskLogs("[INFO]:"+output.getStderr());
					System.out.println(reportModel.getTaskEndTime());
					
					StringBuffer output1 = new StringBuffer();
			          BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			          String line = "";                       
			          while ((line = reader.readLine())!= null) {
			                  output1.append(line + "\n");
			                 
			          }
			          PrintWriter writer = new PrintWriter("/home/jaydeep/Desktop/output1.txt", "UTF-8");
			          consoleOutput.setTaskName(inputdata.getTaskname());
			          consoleOutput.setUserName(inputdata.getUserName());
			          
			          consoleOutput.setConsole(output1.toString());
			          producer.sendMessageConsole(consoleOutput);    
			          writer.println(output1);
					
			          System.out.println(process.getOutputStream());
			          reportModel.setTaskEndTime(new Date(System.currentTimeMillis()));  
					producer.sendReport(reportModel);  
					System.out.println("start time:"+ reportModel.getTaskStartTime());
					System.out.println("end Time:"+ reportModel.getTaskEndTime());
				
					output.setUserName(inputdata.getUserName());
					output.setJobId(inputdata.getJobId());
					output.setTaskName(inputdata.getTaskname());
					producer.sendMessage();
			
		
				
					
			}
	
	}
}