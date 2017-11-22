package com.distributedworkflowengine.resultprocessor.domain;

import org.springframework.stereotype.Component;

@Component
public class UserJob {

	private String jobId;
	private String userName;
	public UserJob() {
		super();
	}
	public UserJob(String jobId, String userName) {
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
