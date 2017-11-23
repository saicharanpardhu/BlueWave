package com.distributedworkflowengine.resultprocessor.domain;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String output;
	private String userName;
	
	public User() {
		super();
	}
	public User(String output, String userName) {
		super();
		this.output = output;
		this.userName = userName;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
