package com.distributedpipelilne.buildAgent.domain;

import org.springframework.stereotype.Component;

/**
 * Getting this data from task-scheduler
 *
 */
@Component
public class Input {
	
	private String jobId;
	private String taskname;
	private String input;
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

}
