package com.distributedworkflowengine.stateinit.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisoprImpl implements Redisopr {
	private static Logger logger=LogManager.getLogger("JsConsumer.class");

	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Override
	
	//<!-- method to save workflow in redis -->
	
	public void saveRedis(Trigger trigger) throws JsonProcessingException {

		 ObjectMapper mapperObj = new ObjectMapper();
		 logger.info("inside redis"); 
	     String jsonStr = mapperObj.writeValueAsString(trigger.getWorkFlow());
	     String jsonStr1 = mapperObj.writeValueAsString(trigger);
	     logger.info("IMPL "+jsonStr);
	     logger.info("trigger2"+ jsonStr1);
	     redisTemplate.opsForValue().set(trigger.getJobId(), jsonStr);
	}

}
