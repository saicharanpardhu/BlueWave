package com.distributedworkflowengine.jobscheduler.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JobInfo implements Serializable{
	
	private String jobId;
	private String workflowName;
	private Map<String,Task> tasks;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Map<String, Task> getTasks() {
		return tasks;
	}
	public void setTaskname(Map<String, Task> tasks) {
		this.tasks = tasks;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	
}

