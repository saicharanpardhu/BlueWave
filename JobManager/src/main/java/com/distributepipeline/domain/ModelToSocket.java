package com.distributepipeline.domain;

import org.springframework.stereotype.Component;

@Component
public class ModelToSocket {

	private String taskName;
    private String userName;
    
    
	public ModelToSocket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModelToSocket(String taskName, String userName) {
		super();
		this.taskName = taskName;
		this.userName = userName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
