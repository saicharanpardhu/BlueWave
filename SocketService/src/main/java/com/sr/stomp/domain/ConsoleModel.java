package com.sr.stomp.domain;

public class ConsoleModel {
	String taskName;
	String userName;
	String console;
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
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public ConsoleModel(String taskName, String userName, String console) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.console = console;
	}
	
	public ConsoleModel() {}
}
