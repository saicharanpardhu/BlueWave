package com.distributedworkflowengine.jobscheduler.controller;
//package in.stackroute.dw.controller;
//
//import java.io.IOException;
//import java.util.*;
//
//import org.json.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.*;
//
//import in.stackroute.dw.domain.Job;
//import in.stackroute.dw.domain.JobInfo;
//import in.stackroute.dw.domain.Task;
//import in.stackroute.dw.domain.TaskToScheduler;
//import in.stackroute.dw.message.JsProducer;
//import in.stackroute.dw.services.JobSchedulerService;
//
//@RestController
//@RequestMapping(value = "/v0.1")
//public class JsController {
//	@Autowired
//	JsProducer producer;
//	@Autowired
//	JobInfo jobInfo;
//	@Autowired
//	Job job;
//	@Autowired
//	TaskToScheduler taskToScheduler;
//	
//	
//	@Autowired
//	JobSchedulerService jobSchedulerService;
//
//		@GetMapping(value="/taskinfo")
//		public ResponseEntity<?> getjobInfo(){
////			
////			jobInfo.setJobId("lskaj");
////			jobInfo.setWorkflowName("dadsfd");
////			String []str= {"1","2"};
////			Task task=new Task("dasjl", "ready",str,str,str);
////			Task task2= new Task("gdsd","dd",str,str,str);
////			
////			Map<String,Task> mp=new HashMap<>();
////			mp.put("clone", task);
////			mp.put("run", task2);
////			
////			
////			jobInfo.setTaskname(mp);
////			
////			JobInfo jobInfo1=new JobInfo();
////			jobInfo1=jobInfo;
////			
////			
//////			System.out.println("printing return data "+jobSchedulerService.selectJob(jobInfo1));
////			
////			Map<String,Task> objectsToTaskScheduler=jobSchedulerService.selectJob(jobInfo1);
////						
////			taskToScheduler.setJobId(jobInfo.getJobId());
////			taskToScheduler.setListOfTasks(objectsToTaskScheduler);
////			
////			producer.sendMessage2(taskToScheduler);
//		    jobSchedulerService.triggerTasks(jobinfo);
//			return new ResponseEntity<Map<String,Task>>(objectsToTaskScheduler,HttpStatus.OK);
//			//return new ResponseEntity<JobInfo>(jobInfo,HttpStatus.OK);
//								
//		}
//}
