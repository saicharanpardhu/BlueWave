package com.distributedpipelilne.buildAgent.message;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.buildAgent.domain.Output;
import com.distributedpipelilne.buildAgent.domain.Input;


@Service
public class BuildConsumer {
	
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	 
	 @Autowired
	 Output output;
	
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

	@KafkaListener(topics = "Build", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
		
				String fname="";
				//path of the folder where command is to be exec.
				String path = "/home/prashant/Desktop/Task_source";
				File dir = new File(path);
				File[] fList = dir.listFiles();
				for(File file:fList) {
				if (file.isDirectory()) {
					fname = file.getName();
				        }
				}	
				File directory = new File(path+"/"+fname);
				System.out.println("Task Processing...");
				Process process = Runtime.getRuntime().exec("mvn build",null,directory);
				process.waitFor();
				if(process.exitValue()==0) {
					String []str= {"Task Complete"};
					output.setOutput(str);
					output.setErrcode(200);
					output.setStderr("task complete");
					System.out.println("task complete");
				}
					    
			}

}
