package in.stackroute.dw.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import in.stackroute.dw.domain.JobInfo;
import in.stackroute.dw.domain.Model;
import in.stackroute.dw.domain.Task;

@Service
public class ResultService {

	@Autowired
	private RedisTemplate<String , String> redisTemplate ;
	
	
	  public void updateRedis(Model model) throws IOException
	    {
//		  System.out.println("sdfsdfsd");
	        
		  String jobId=model.getJobId();
		  JobInfo jobInfo=getRedis(jobId);
//		  
//		  System.out.println(jobId);
//		  
		  ObjectMapper mapperObj = new ObjectMapper();
	      String jsonStr = mapperObj.writeValueAsString(jobInfo);
	        jobInfo.getTasks().get(model.getTaskName()).setStatus("complete");
	      
	     System.out.println("Task "+model.getTaskName());   
	        
	     ObjectMapper mapperObj2 = new ObjectMapper();
	     String jsonStrNew = mapperObj2.writeValueAsString(jobInfo);
	     
	     redisTemplate.opsForValue().set(model.getJobId(), jsonStrNew);
	     System.out.println(jsonStrNew);

//	        redisTemplate.opsForValue().set(model.getType(), jsonStr);
	        
	    }   
	  
	  
	  public JobInfo getRedis(String jobId) throws JsonParseException, JsonMappingException, IOException 
		{
			String str=redisTemplate.opsForValue().get(jobId);
//			System.out.println(str);
			Gson gson = new Gson();
			JobInfo jobInfo = gson.fromJson(str, JobInfo.class);
			return jobInfo;
		}
}
