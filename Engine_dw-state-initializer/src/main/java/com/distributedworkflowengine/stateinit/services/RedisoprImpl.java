package com.distributedworkflowengine.stateinit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisoprImpl implements Redisopr {

	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Override
	
	//<!-- method to save workflow in redis -->
	
	public void saveRedis(Trigger trigger) throws JsonProcessingException {

		 ObjectMapper mapperObj = new ObjectMapper();
		 System.out.println("inside redis"); 
	     String jsonStr = mapperObj.writeValueAsString(trigger.getWorkFlow());
	     String jsonStr1 = mapperObj.writeValueAsString(trigger);
	     System.out.println("IMPL "+jsonStr);
	     System.out.println("trigger2"+ jsonStr1);
	     redisTemplate.opsForValue().set(trigger.getJobId(), jsonStr);
	}

}
