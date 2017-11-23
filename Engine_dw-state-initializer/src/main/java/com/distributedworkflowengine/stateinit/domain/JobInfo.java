package com.distributedworkflowengine.stateinit.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
@Component
public class JobInfo{
    
    private String jobId;
    private String workflowName;
    private Map<String,Task> tasks;
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public JobInfo() {
		super();
	}
	public JobInfo(String jobId, String workflowName, Map<String, Task> tasks) {
		super();
		this.jobId = jobId;
		this.workflowName = workflowName;
		this.tasks = tasks;
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
