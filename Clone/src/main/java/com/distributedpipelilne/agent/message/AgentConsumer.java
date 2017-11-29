package com.distributedpipelilne.agent.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.agent.model.Input;
import com.distributedpipelilne.agent.model.Output;
import com.distributedpipelilne.agent.model.ReportModel;


@Service
public class AgentConsumer {
	
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 @Autowired
	 ReportModel reportModel;
	 
	 @Autowired
	 Output output;
	 
	 @Autowired
	 AgentProducer agentProducer;
	
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


	@KafkaListener(topics = "Clone", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
				String rex1 = "(\\w+://)(.+@)*([\\w\\d\\.]+)(:[\\d]+){0,1}/*(.*)";
		        String rex2 = "(.+@)*([\\w\\d\\.]+):(.*)";		       	        
		        
			    this.jobid = inputdata.getJobId();
			    this.taskname = inputdata.getTaskname();
			    System.out.println("printing username"+ inputdata.getUserName());
			    
			    System.out.println("id" + this.jobid);
			    
			    String url= inputdata.getInput()[0];
			    System.out.println("url : "+ url);
			    latch.countDown();
			    Runtime.getRuntime().exec("mkdir /home/jaydeep/Desktop/Task_source/"+inputdata.getJobId()+"/");
			    
				File dir = new File("/home/jaydeep/Desktop/Task_source/"+inputdata.getJobId()+"/");
//				File dirshell = new File("/home/jaydeep/Desktop/main/Stackroute/Spring/agent/");
				
				reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
				reportModel.setTaskAlias(inputdata.getTaskname());
				reportModel.setJobId(inputdata.getJobId());			
				agentProducer.sendReport(reportModel);
				
				System.out.println("start time:"+ reportModel.getTaskStartTime());
					
					try {
			            java.util.regex.Pattern p  = java.util.regex.Pattern.compile(rex1);
			            Matcher m = p.matcher(url);
			            boolean b= m.matches();
			            java.util.regex.Pattern p1  = java.util.regex.Pattern.compile(rex2);
			            Matcher m1 = p1.matcher(url);
			            boolean b1= m1.matches();
			            
			            if (b == true & b1 == true){
			                System.out.println("Task Processing...");
		                Process process = Runtime.getRuntime().exec("git clone "+url,null,dir);
			                System.out.println("jsdfalkkjh");
			                process.waitFor();
			                while(process.exitValue()==0) {
			                	String []str= {"Clone Task Complete"};
								output.setOutput(str);
								output.setErrcode(200);
								output.setStderr("task complete");
								output.setUserName(inputdata.getUserName());

								
								System.out.println(reportModel.getTaskEndTime());
								
								System.out.println("task complete");
			                    break;
			                }
			            }
			            else {
			            	String []str= {"url is invalid"};
			            	output.setOutput(str);
							output.setErrcode(400);
							output.setUserName(inputdata.getUserName());

			            	output.setStderr(inputdata.getTaskname()+"failed");
			                System.out.println("failed");
			            }
			            
			            
			        } catch (Exception e) {
			        	String[] str= {"task failed"};
			        	output.setOutput(str);
						output.setErrcode(400);
			        	output.setStderr(inputdata.getTaskname()+"failed");
						output.setUserName(inputdata.getUserName());

			            System.out.println("Task failed");
			        }
					
					System.out.println("end time:"+ reportModel.getTaskEndTime());
					output.setJobId(inputdata.getJobId());
					output.setTaskName(inputdata.getTaskname());
					output.setUserName(inputdata.getUserName());
					System.out.println("username_consumer "+ inputdata.getUserName());

//					System.out.println("after setting job Id"+ inputdata.getJobId());
//					System.out.println("347987 "+ inputdata.getUserName());
					agentProducer.sendMessage(output);
					
					reportModel.setTaskAlias(inputdata.getTaskname());
					reportModel.setJobId(inputdata.getJobId());
					reportModel.setTaskLogs("[INFO]:"+output.getStderr());
					reportModel.setTaskEndTime(new Date(System.currentTimeMillis()));
					System.out.println("end time:"+ reportModel.getTaskEndTime());
					agentProducer.sendReport(reportModel);

	}

}