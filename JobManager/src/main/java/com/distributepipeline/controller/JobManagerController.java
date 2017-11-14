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
    
	//for resource sharing
	
	@CrossOrigin(origins="*") 
	
	//getting workflow name from user using request mapping
	
	@RequestMapping(path="/workflowname/{workFlowName}") 
	public ResponseEntity<?> echoWord(@PathVariable String workFlowName) {
		
		//sending workflow name to workflow persistence
		
		System.out.println("sending...");
		jobManagerProducer.sendWorkFlowName(workFlowName); 
		return new ResponseEntity<String>(workFlowName, HttpStatus.OK); 
	}  
    //method to add a new user
    }
