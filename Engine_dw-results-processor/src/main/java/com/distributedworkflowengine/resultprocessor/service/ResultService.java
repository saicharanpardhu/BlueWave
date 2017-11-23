package com.distributedworkflowengine.resultprocessor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.distributedworkflowengine.resultprocessor.domain.Model;
import com.distributedworkflowengine.resultprocessor.domain.WorkFlow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class ResultService {

	@Autowired
	private RedisTemplate<String , String> redisTemplate ;
	
	  //method to update redis as per the task status
	  public void updateRedis(Model model) throws JsonProcessingException 
	    {
		  System.out.println("jId " + model.getJobId());
	        
		  String jobId=model.getJobId();
//taking data in model Trigger
//		  JobInfo jobInfo=getRedis(jobId);

		  
		  
		  WorkFlow jobInfo=getRedis(jobId);
//		  
//		  System.out.println(jobId);
//		  
		  ObjectMapper mapperObj = new ObjectMapper();
	      String jsonStr = mapperObj.writeValueAsString(jobInfo);
	      
	      System.out.println("Task "+model.getTaskName());
	      

	     jobInfo.getTasks().get(model.getTaskName()).setStatus("complete");
 
	     System.out.println("Task2 "+model.getTaskName());   
	        
	     ObjectMapper mapperObj2 = new ObjectMapper();
	     String jsonStrNew = mapperObj2.writeValueAsString(jobInfo);
	     
	     redisTemplate.opsForValue().set(model.getJobId(), jsonStrNew);
	     System.out.println(jsonStrNew);
  
	    }   
	  
	  //method to return workflow 
	  public WorkFlow getRedis(String jobId)
		{
			String str=redisTemplate.opsForValue().get(jobId);
			System.out.println(str);
			Gson gson = new Gson();
			WorkFlow jobInfo = gson.fromJson(str, WorkFlow.class);
			return jobInfo;
		}
}
