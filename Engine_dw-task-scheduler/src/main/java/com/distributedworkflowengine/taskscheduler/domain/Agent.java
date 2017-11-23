package com.distributedworkflowengine.taskscheduler.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Agent {

	private String jobId;
	private String taskname;
	private String[] input;
	private String userName;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getJobId() {
		return jobId;
	}
	public Agent(String jobId, String taskname, String[] input, String userName, String type) {
		super();
		this.jobId = jobId;
		this.taskname = taskname;
		this.input = input;
		this.userName = userName;
		this.type = type;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}
//	public Agent(String jobId, String taskname, String[] input) {
//		super();
//		this.jobId = jobId;
//		this.taskname = taskname;
//		this.input = input;
//	}
	public Agent() {}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
