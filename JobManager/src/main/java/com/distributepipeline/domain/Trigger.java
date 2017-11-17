package com.distributepipeline.domain;

import org.springframework.stereotype.Component;

@Component
public class Trigger {
	
	//fields for the trigger class
	
	private String jobId;
	private String userName;
	private WorkFlow workFlow;
	
	public Trigger() {
		super();
	}
	
	//constructor for the trigger class
	
	public Trigger(String jobId, String userName, WorkFlow workFlow) {
		super();
		this.jobId = jobId;
		this.userName=userName;
		this.workFlow = workFlow;
	}
	
	//getters and setters for the trigger class
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public WorkFlow getWorkflow() {
		return workFlow;
	}
	public void setWorkflow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
