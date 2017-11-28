package com.sr.stomp.domain;

public class SocketModel {
	String taskName;
	String userName;
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
	public SocketModel(String taskName, String userName) {
		super();
		this.taskName = taskName;
		this.userName = userName;
	}
	
	public SocketModel() {}
}
