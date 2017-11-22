package com.distributedworkflowengine.stateinit.domain;

import org.springframework.stereotype.Component;

@Component
public class Trigger {
    private String jobId;
    private String userName;
    private WorkFlow workFlow;
    public Trigger() {
        super();
    }
 
    public Trigger(String jobId, String userName, WorkFlow workFlow) {
		super();
		this.jobId = jobId;
		this.userName = userName;
		this.workFlow = workFlow;
	}

//	public Trigger() {
//		super();
//	}

	public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public WorkFlow getWorkFlow() {
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