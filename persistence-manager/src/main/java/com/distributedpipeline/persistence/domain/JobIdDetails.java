package com.distributedpipeline.persistence.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="JobIdDetails")
public class JobIdDetails {
	
	@Id
	private String jobId;
    private String userName;
    private String workFlowName;
	
	/*--------------------- Getters and Setters for the fields -----------------------------*/
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
	
	
	public JobIdDetails() {
		super();
	}

	public JobIdDetails(String jobId, String userName, String workFlowName) {
		super();
		this.jobId = jobId;
		this.userName = userName;
		this.workFlowName = workFlowName;
	}
	
	

}
