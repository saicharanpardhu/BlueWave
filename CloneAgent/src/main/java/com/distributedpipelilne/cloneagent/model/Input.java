package com.distributedpipelilne.cloneagent.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public class Input implements Serializable {
	
	private String jobId;
	private String taskname;
	private String input;
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public Input() {
		super();
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
