package com.distributedworkflowengine.stateinit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distributedworkflowengine.stateinit.domain.WorkFlow;
import com.distributedworkflowengine.stateinit.messenger.StateInitializerProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

//<!-- Redis crud operations -->

@RestController
@RequestMapping(path="/v1.0/Engine")
public class RedisController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	StateInitializerProducer engine;

	//<!-- method to save workflow in redis -->
	
	@PostMapping("/saveRedis/{name}")
	public ResponseEntity<WorkFlow> saveRedis(@PathVariable String name,@RequestBody  WorkFlow jobinfo) throws JsonProcessingException
	{
		
		

		 ObjectMapper mapperObj = new ObjectMapper();
	     String jsonStr = mapperObj.writeValueAsString(jobinfo);
	     redisTemplate.opsForValue().set(name, jsonStr);
		return new ResponseEntity<WorkFlow>(jobinfo,HttpStatus.OK);
		
	}
	
	//<!-- method to get workflow from redis by workflow name-->

	
	@GetMapping("/getRedisbyId/{name}")
	public ResponseEntity<WorkFlow> getRedis(@PathVariable String name) throws JsonParseException, JsonMappingException, IOException 
	{
		String str=redisTemplate.opsForValue().get(name);
		System.out.println(str);
		Gson gson = new Gson();
		WorkFlow JobInfo = gson.fromJson(str, WorkFlow.class);
		return new ResponseEntity<WorkFlow>(JobInfo,HttpStatus.OK);
	}

	//<!-- method to get all workflows-->
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getRedis() 
	{
			StringBuffer sb = new StringBuffer();

	       Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());

	       Iterator<byte[]> it = keys.iterator();

	       while(it.hasNext()){

	           byte[] data = (byte[])it.next();
	           sb.append(new String(data, 0, data.length));
	           sb.append(" ");
	              }
	       String []AllKeys=sb.toString().split(" ");
	       List<WorkFlow> lst=new ArrayList<WorkFlow>();
	       ValueOperations<String, String> ob=  redisTemplate.opsForValue();
	       for(String key:AllKeys)
	   		{
	    	   Gson gson = new Gson();
	   			WorkFlow JobInfo = gson.fromJson(ob.get(key), WorkFlow.class);
	   			lst.add(JobInfo);
	   		}

		return new ResponseEntity<List<WorkFlow>>(lst,HttpStatus.OK);
	}

}
