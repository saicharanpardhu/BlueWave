package com.distributedworkflowengine.jobscheduler.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.Task;
import com.distributedworkflowengine.jobscheduler.domain.TaskToScheduler;
import com.distributedworkflowengine.jobscheduler.domain.WorkFlow;
import com.distributedworkflowengine.jobscheduler.message.JsProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Service
public class JobSchedulerServiceImpl implements JobSchedulerService{
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	WorkFlow jobinfo;
	@Autowired
	TaskToScheduler taskToScheduler;
	@Autowired
	JsProducer producer;
	
	public Map<String,Task> selectJob(WorkFlow jobInfo,String job) throws JsonProcessingException {
		
		
			
		int j=jobInfo.getTasks().size();
		
		Map<String,Task> mp=jobInfo.getTasks();
		
		Map<String,Task> mapObjectToScheduler=new HashMap<String,Task>();

			Set<String > keys=mp.keySet();
		
			for(String key:keys)
			{
				System.out.println("key1 "+key + " status: "+ jobInfo.getTasks().get(key).getStatus());
			}
			
			for(String key:keys)
			{				
				String[]  dependency=jobInfo.getTasks().get(key).getDepends_on() ;
				String  taskStatus=jobInfo.getTasks().get(key).getStatus() ;

				
				System.out.println("tStatus "+taskStatus);
				
				System.out.println("dep1 ");

	//Don't run if task is complete or scheduled	
				
			if(!(taskStatus.equals("complete")) && !(taskStatus.equals("scheduled"))) {
				
				if(dependency==null ) {
					mapObjectToScheduler.put(key, jobInfo.getTasks().get(key));
					jobInfo.getTasks().get(key).setStatus("scheduled");	
					
					ObjectMapper mapperObj=new ObjectMapper();
					String jsonStr=mapperObj.writeValueAsString(jobInfo);
					redisTemplate.opsForValue().set(job, jsonStr);

				}
				else if(dependency!=null)  {
					
					String[] dependsOn=jobInfo.getTasks().get(key).getDepends_on();
					System.out.println("HI" + dependsOn[0]+ " length:" + dependsOn.length);
					
					{
					int flag=0;
					
					for(int i=0;i<dependsOn.length;i++) {
						if(jobInfo.getTasks().get(dependsOn[i]).getStatus().equals("complete")) {
							flag=0;		
						}
						else {
							flag=1;
							break;
						}
					}
					
					
					if(flag==0) {
						mapObjectToScheduler.put(key, jobInfo.getTasks().get(key));
						jobInfo.getTasks().get(key).setStatus("scheduled");

						ObjectMapper mapperObj=new ObjectMapper();
						String jsonStr=mapperObj.writeValueAsString(jobInfo);
						redisTemplate.opsForValue().set(job, jsonStr);
					}
				}
				}
			}
			}

	Set<String > keys2=mapObjectToScheduler.keySet();
			
	for(String key:keys2)
			{
				System.out.println("key2 "+key);
			}
	
	return mapObjectToScheduler;
			
	}

			
			
	@Override
	public WorkFlow getData(String jobid) {

        String str=redisTemplate.opsForValue().get(jobid);
//        System.out.println(str);
        Gson gson = new Gson();
        WorkFlow jobinfo = gson.fromJson(str, WorkFlow.class);
//        System.out.println(jobinfo.getTasks()+"dfsdfs");
        return jobinfo;
	}

	
	
}
