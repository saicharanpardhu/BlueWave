package com.distributedworkflowengine.taskscheduler.domain;

import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class Model {
	private String jobId;
	private String userName;
	private String workFlowName;
	private Map<String,Task> listOfTasks;
	public Model(String jobId, String userName, Map<String, Task> listOfTasks) {
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
	

	
	public Map<String, Task> getListOfTasks() {
		return listOfTasks;
	}
	public void setListOfTasks(Map<String, Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}
	public Model() {}
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
