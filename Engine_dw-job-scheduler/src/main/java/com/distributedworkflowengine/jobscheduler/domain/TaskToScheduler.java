package com.distributedworkflowengine.jobscheduler.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TaskToScheduler {
	
	private String jobId;
	private String userName;
	private Map<String,Task> listOfTasks;
	private String workFlowName;
	
	public TaskToScheduler(String jobId, String userName, Map<String, Task> listOfTasks) {
		super();
		this.jobId = jobId;
		this.userName = userName;
		this.listOfTasks = listOfTasks;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Map<String,Task> getListOfTasks() {
		return listOfTasks;
	}
	public void setListOfTasks(Map<String,Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}
	public TaskToScheduler() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	
}
