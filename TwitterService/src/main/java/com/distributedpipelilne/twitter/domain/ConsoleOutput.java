package com.distributedpipelilne.twitter.domain;

import org.springframework.stereotype.Component;


/*
 * this model will be send to User with the given data
 */
@Component
public class ConsoleOutput {

	String taskName;
    String userName;
    String console;
    
 
	public ConsoleOutput(String taskName, String userName, String console) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.console = console;
	}
	public String getTaskName() {
		return taskName;
	}
	public ConsoleOutput() {
		super();
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
}
