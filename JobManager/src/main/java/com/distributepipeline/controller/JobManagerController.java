package com.distributepipeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.distributepipeline.domain.WorkFlow;
import com.distributepipeline.message.JobManagerProducer;


@RestController
@RequestMapping("/v1.0")

public class JobManagerController{
   
	@Autowired
	JobManagerProducer jobManagerProducer;
    
	
	@Autowired
	JobManagerProducer producer;
	//for resource sharing
	private String userName;
	
	@CrossOrigin(origins="*") 
	
	//getting workflow name from user using request mapping
	
	@RequestMapping(path="/workflowname/{userName}/{workFlowName}") 
	public ResponseEntity<?> echoWord(@PathVariable("userName") String userName ,@PathVariable("workFlowName") String workFlowName) {
		
		//sending workflow name to workflow persistence
		
		System.out.println("sending...");
		System.out.println(workFlowName);
		this.userName=userName;
//		producer.jobIdDetailsToPersistence(userName, workFlowName);
		jobManagerProducer.sendWorkFlowName(workFlowName); 
		return new ResponseEntity<String>(workFlowName, HttpStatus.OK); 
	}  
    //method to add a new user

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    }
