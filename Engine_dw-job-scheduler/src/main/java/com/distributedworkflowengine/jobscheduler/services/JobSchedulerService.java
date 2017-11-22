package com.distributedworkflowengine.jobscheduler.services;

import java.util.Map;

import com.distributedworkflowengine.jobscheduler.domain.Task;
import com.distributedworkflowengine.jobscheduler.domain.WorkFlow;
import com.fasterxml.jackson.core.JsonProcessingException;


//Interface for Job Scheduler Service

public interface JobSchedulerService {
	
	public Map<String,Task> selectJob(WorkFlow jobInfo,String job,String userName) throws JsonProcessingException ; 
	public WorkFlow getData(String jobid);
	public String workFlowStatus(String jobid,String userName, WorkFlow jobInfo);

}
