package com.distributedpipelilne.cloneagent.message;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.distributedpipelilne.cloneagent.model.Input;
import com.distributedpipelilne.cloneagent.model.Output;


@Service
public class CloneAgentConsumer {
	
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	 
	 @Autowired
	 Output output;
	 
	 @Autowired
	 CloneAgentProducer agentProducer;
	
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

	@KafkaListener(topics = "clone", 
			  containerFactory = "reportKafkaListenerContainerFactory")
			public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
			    System.out.println(inputdata.getInput());
			    
			    this.jobid = inputdata.getJobId();
			    this.taskname = inputdata.getTaskname();
			    
			    String url= inputdata.getInput();
			    latch.countDown();
			    Runtime.getRuntime().exec("mkdir /home/jaydeep/Desktop/Task_source");
			    
				File dir = new File("/home/jaydeep/Desktop/Task_source");
				System.out.println("Task Processing...");
					Process process = Runtime.getRuntime().exec("git clone "+url,null,dir);
					process.waitFor();
					if(process.exitValue()==0) {
						String []str= {"Task Complete"};
						output.setOutput(str);
						output.setErrcode(200);
						output.setStderr("task complete");
						System.out.println("task complete");
						

					}
					agentProducer.sendMessage();
//					else {
//						output.setErrorStatus("url is invalid");
//						System.out.println("url is invalid");
//						
//					}
	}

}
