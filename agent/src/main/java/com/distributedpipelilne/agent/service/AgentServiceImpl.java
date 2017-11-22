//package com.distributedpipelilne.agent.service;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//
//import com.distributedpipelilne.agent.message.AgentConsumer;
//import com.distributedpipelilne.agent.model.Input;
//
//public class AgentServiceImpl implements AgentService {
//	
//	@Autowired
//	AgentConsumer agent;
//
//	@Override
//	public String gitClone() throws IOException, InterruptedException {
//		Input url = agent.getInput();
//		Runtime.getRuntime().exec("mkdir /home/jaydeep/Desktop/Task_source");
//
//		File dir = new File("/home/prashant/Desktop/Task_source");
//		System.out.println("Task Processing...");
//			Process process = Runtime.getRuntime().exec("git clone "+url,null,dir);
//			process.waitFor();
//			String outputStatus = null;
//			String ErrorStauts = null;
//			if(process.exitValue()==0) {
//				outputStatus = "Task Complete";
//				System.out.println(outputStatus);
//				return outputStatus;
//			}
//			else {
//				ErrorStauts = "invaild url";
//				System.out.println(ErrorStauts);
//				return outputStatus;
//				
//			}
//	}
//		
//	
//	
//
//}
