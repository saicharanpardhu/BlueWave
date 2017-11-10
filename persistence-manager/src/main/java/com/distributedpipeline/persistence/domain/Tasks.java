package com.distributedpipeline.persistence.domain;

import javax.validation.constraints.NotNull;

public class Tasks {

	@NotNull
	private String taskType;
	private String status;
	private String[] depends_on;
	private String[] output;
	private String[] input;
	public String getTaskType() {
		return taskType;
	}	
	
	/*------------------------ Getters and Setters for the fields-----------------------------*/
		
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String[] getDepends_on() {
		return depends_on;
	}

	public void setDepends_on(String[] depends_on) {
		this.depends_on = depends_on;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getOutput() {
		return output;
	}
	public void setOutput(String[] output) {
		this.output = output;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}

	public Tasks() {
		super();
	}

}
