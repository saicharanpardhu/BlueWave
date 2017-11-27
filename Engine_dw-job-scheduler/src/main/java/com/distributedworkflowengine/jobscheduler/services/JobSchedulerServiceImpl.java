package com.distributedworkflowengine.jobscheduler.services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.distributedworkflowengine.jobscheduler.domain.ReportModel;
import com.distributedworkflowengine.jobscheduler.domain.Task;
import com.distributedworkflowengine.jobscheduler.domain.TaskToScheduler;
import com.distributedworkflowengine.jobscheduler.domain.User;
import com.distributedworkflowengine.jobscheduler.domain.WorkFlow;
import com.distributedworkflowengine.jobscheduler.message.JsProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Service
public class JobSchedulerServiceImpl implements JobSchedulerService{
	private static Logger logger=LogManager.getLogger("JobSchedulerServiceImpl.class");

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	WorkFlow jobinfo;
	@Autowired
	TaskToScheduler taskToScheduler;
	@Autowired
	User user;
	
	@Autowired
	ReportModel reportModel;
	
	@Autowired
	JsProducer producer;

	
	//method to check whether to schedule a task or not and 
	//returning the map object to producer containing scheduled task
	//updating redis as per the task status 
	
	public Map<String,Task> selectJob(WorkFlow jobInfo,String job,String userName) throws JsonProcessingException {
		Map<String,Task> mp=jobInfo.getTasks();
		Map<String,Task> mapObjectToScheduler=new HashMap<String,Task>();
		Set<String > keys=mp.keySet();
		for(String key:keys)
		{				
			String[]  dependency=jobInfo.getTasks().get(key).getDepends_on() ;
			String  taskStatus=jobInfo.getTasks().get(key).getStatus() ;
			if(taskStatus!=null && taskStatus.equals("failed"))
				break;
			if(taskStatus!=null && !(taskStatus.equals("complete")) && !(taskStatus.equals("scheduled"))) {

				
				if(dependency==null) {
					mapObjectToScheduler.put(key, jobInfo.getTasks().get(key));
					jobInfo.getTasks().get(key).setStatus("scheduled");	

					ObjectMapper mapperObj=new ObjectMapper();
					String jsonStr=mapperObj.writeValueAsString(jobInfo);
					redisTemplate.opsForValue().set(job, jsonStr);

				}
				else if(dependency!=null)  {

					String[] dependsOn=jobInfo.getTasks().get(key).getDepends_on();
					logger.info("HI" + dependsOn[0]+ " length:" + dependsOn.length);

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
		
		
			workFlowStatus(job,userName,jobInfo);
		return mapObjectToScheduler;
	}

	//method to get data from redis for a particular jobid
	@Override
	public WorkFlow getData(String jobid) {
		String str=redisTemplate.opsForValue().get(jobid);
		Gson gson = new Gson();
		WorkFlow jobinfo = gson.fromJson(str, WorkFlow.class);
		return jobinfo;
	}

	@Override
	public String workFlowStatus(String jobId,String userName, WorkFlow jobInfo) {
		Map<String,Task> map=jobInfo.getTasks();
		Set<String > Keys=map.keySet();
		int clock=0;
		user.setJobId(jobId);
		user.setUserName(userName);
		
		
		for(String key1:Keys)
		{	
			String[]  dependency=jobInfo.getTasks().get(key1).getDepends_on() ;
			String  taskStatus=jobInfo.getTasks().get(key1).getStatus() ;
			if(taskStatus!=null && taskStatus=="failed")
				break;
			if(taskStatus!=null && (!taskStatus.equals("complete")))
					clock=1;
			
		}
		if(clock==0)
		{
			reportModel.setJobEndTime(new Timestamp(System.currentTimeMillis()));
			reportModel.setJobId(user.getJobId());
			WorkFlow workFlow=new WorkFlow();
			workFlow=getData(user.getJobId());
			Map<String,Task> mp=new HashMap<String,Task>();
			mp=workFlow.getTasks();
			Set<String> keys=mp.keySet();
			for(String key:keys)
			{
				reportModel.setTaskAlias(key);
				reportModel.setJobStatus("completed");
//				producer.sendToPersistence(jobInfo.getOwner(), reportModel.getJobEndTime());
				producer.sendToReport(reportModel);

			}
			producer.sendToSocket(user);
		}
			
		return null;
	}


}
