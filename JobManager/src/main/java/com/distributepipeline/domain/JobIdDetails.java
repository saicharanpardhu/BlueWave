package com.distributepipeline.domain;

import org.springframework.stereotype.Component;

@Component
public class JobIdDetails {
	
	private String jobId;
	private String userName;
	private String workFlowName;
	
	public JobIdDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobIdDetails(String jobId, String userName, String workFlowName) {
		super();
		this.jobId = jobId;
		this.userName = userName;
		this.workFlowName = workFlowName;
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
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

}
