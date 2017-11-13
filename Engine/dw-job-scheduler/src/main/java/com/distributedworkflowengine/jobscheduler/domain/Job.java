package com.distributedworkflowengine.jobscheduler.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Job implements Serializable{
	
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

}
