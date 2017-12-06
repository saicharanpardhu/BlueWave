package com.distributedpipelilne.emailService.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/*
 * model to represent Result-processor
 */

@Component
@Table
public class ReportModel {
    
  private String jobId; //job-id
    
    @Column
   private String workFlowName; //name of the workflow
    
    @Column
   private Date jobStartTime;
    
    @Column
    private Date jobEndTime;
    
    @Column
    private String jobStatus;
    
   private String taskAlias;
    
    public ReportModel(String jobId, String workFlowName, Date jobStartTime, Date jobEndTime, String jobStatus,
		String taskAlias, Date taskStartTime, Date taskEndTime, String taskLogs) {
	super();
	this.jobId = jobId;
	this.workFlowName = workFlowName;
	this.jobStartTime = jobStartTime;
	this.jobEndTime = jobEndTime;
	this.jobStatus = jobStatus;
	this.taskAlias = taskAlias;
	this.taskStartTime = taskStartTime;
	this.taskEndTime = taskEndTime;
	this.taskLogs = taskLogs;
}

	@Column
   private Date taskStartTime;
    
    @Column
   private Date taskEndTime;
    
    @Column
   private String taskLogs;
    
    public String getTaskAlias() {
        return taskAlias;
    }

    public void setTaskAlias(String taskAlias) {
        this.taskAlias = taskAlias;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public ReportModel() {
		super();
	}

	public String getTaskLogs() {
        return taskLogs;
    }

    public void setTaskLogs(String taskLogs) {
        this.taskLogs = taskLogs;
    }




    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    
    
}