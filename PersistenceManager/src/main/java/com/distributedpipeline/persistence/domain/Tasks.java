package com.distributedpipeline.persistence.domain;

import javax.validation.constraints.NotNull;

public class Tasks {

	@NotNull
	private String taskNameAlias;	
	@NotNull
	private String taskType;
	private String[] dependsOn;
	private String[] output;
	private String[] input;
	
	
	/*------------------------ Getters and Setters for the fields-----------------------------*/
	public String getTaskNameAlias() {
		return taskNameAlias;
	}
	public void setTaskNameAlias(String taskNameAlias) {
		this.taskNameAlias = taskNameAlias;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String[] getDependsOn() {
		return dependsOn;
	}
	public void setDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
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
	public Tasks(String taskNameAlias, String taskType, String[] dependsOn, String[] output, String[] input) {
		super();
		this.taskNameAlias = taskNameAlias;
		this.taskType = taskType;
		this.dependsOn = dependsOn;
		this.output = output;
		this.input = input;
	}
	public Tasks() {
		super();
	}
	
	
	
}
