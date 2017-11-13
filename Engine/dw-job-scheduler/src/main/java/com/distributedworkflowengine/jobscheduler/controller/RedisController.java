package com.distributedworkflowengine.jobscheduler.controller;

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

import com.distributedworkflowengine.jobscheduler.domain.Task;
import com.distributedworkflowengine.jobscheduler.domain.WorkFlow;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
@RestController
@RequestMapping(path="/v1.0/Engine")
public class RedisController {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
   
    
    @PostMapping("/saveRedis")
    public ResponseEntity<Task> saveRedis(@RequestBody Task task) throws JsonProcessingException
    {
        
         ObjectMapper mapperObj = new ObjectMapper();
         String jsonStr = mapperObj.writeValueAsString(task);
         
         redisTemplate.opsForValue().set(task.getType(), jsonStr);
//      redisTemplate.opsForHash().put(task.getType(),"task1",jsonStr);
    
//      redisTemplate.opsForValue().set(task.getTaskName(), "nehara");
        return new ResponseEntity<Task>(task,HttpStatus.OK);
        
    }
    @GetMapping("/getRedisbyId/{name}")
    public ResponseEntity<?> getRedis(@PathVariable String name) throws JsonParseException, JsonMappingException, IOException 
    {
        String str=redisTemplate.opsForValue().get(name);
        System.out.println(name);
        Gson gson = new Gson();
        WorkFlow jobinfo = gson.fromJson(str, WorkFlow.class);
        return new ResponseEntity<WorkFlow>(jobinfo,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getRedis() throws JsonProcessingException
    {
//      Object ob=  redisTemplate.opsForHash().g
//      ValueOperations<String, String> ob=  redisTemplate.opsForValue();
//      
//      System.out.println(ob.get("clone"));
//      Set<String> keys = redisTemplate.keys("clone");
//
//      List<Task> lst=new ArrayList<Task>();
//      Gson gson = new Gson();
//      
//      
//      
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
                WorkFlow jobinfo = gson.fromJson(ob.get(key), WorkFlow.class);
                lst.add(jobinfo);
            }
        return new ResponseEntity<List<WorkFlow>>(lst,HttpStatus.OK);
    }
    
    
}