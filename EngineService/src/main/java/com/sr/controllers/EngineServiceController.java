package com.sr.controllers;
 

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sr.domain.EngineModel;
import com.sr.messenger.EngineServiceConsumer;
import com.sr.messenger.EngineServiceProducer;
import com.sr.utility.LogExecutionTime;

@Controller    
@RequestMapping(path="/v1.0/Engineservice")
//@Api(value="Reporting Service", description="Reporting microservice of the Distributed Pipeline Project.")
public class EngineServiceController {
  
	@Autowired
	EngineServiceProducer engineProducer;
	
	@GetMapping("/workflow")
	@LogExecutionTime
	public @ResponseBody ResponseEntity<?> fetchWorkflow () {
		
		ArrayList<Long> arr=new ArrayList<>();
		arr.add((long)2);
		arr.add((long)1);
		EngineModel engine=new EngineModel((long)1,arr,"vishal");
		engineProducer.sendMessage(engine);
		return null;
	
	}
}
	
