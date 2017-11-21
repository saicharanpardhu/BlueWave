package com.distributedworkflowengine.jobscheduler.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String jobId;
	private String userName;
	public User() {
		super();
	}
	public User(String jobId, String userName) {
		super();
		this.jobId = jobId;
		this.userName = userName;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
