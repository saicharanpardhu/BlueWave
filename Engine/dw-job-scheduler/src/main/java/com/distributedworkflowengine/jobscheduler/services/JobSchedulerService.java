package com.distributedworkflowengine.jobscheduler.services;

import java.util.List;
import java.util.Map;

import com.distributedworkflowengine.jobscheduler.domain.Task;
import com.distributedworkflowengine.jobscheduler.domain.WorkFlow;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface JobSchedulerService {
	
	public Map<String,Task> selectJob(WorkFlow jobInfo,String job) throws JsonProcessingException ; 
	public WorkFlow getData(String jobid);

}
