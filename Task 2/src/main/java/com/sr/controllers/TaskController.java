package com.sr.controllers;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.sr.services.Task1Service; 

import io.swagger.annotations.Api; 

@Controller    
@RequestMapping(path="/v1.0/task1service/task")
@Api(value="Task 1", description="Task1 microservice of the Distributed Pipeline Project.")
public class TaskController {
	
		@Autowired
		private Task1Service taskService;
		
		@GetMapping
		public @ResponseBody ResponseEntity<String> getTask () {  
			try { 
				return new ResponseEntity<String>(taskService.executeTask1(), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} 	
}
