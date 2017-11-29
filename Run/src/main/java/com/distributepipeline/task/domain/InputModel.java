package com.distributepipeline.task.domain;

import org.springframework.stereotype.Component;

@Component
public class InputModel {
	private String jobId;
	private String taskname;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String[] input;
	private String userName;
	private String type;
	
	
	public InputModel(String jobId, String taskname, String[] input, String userName, String type) {
		super();
		this.jobId = jobId;
		this.taskname = taskname;
		this.input = input;
		this.userName = userName;
		this.type = type;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}
	public InputModel() {
		super();
	}
	public String getJobId() {
		return jobId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}