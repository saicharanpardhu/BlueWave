package com.distributedworkflowengine.stateinit.domain;

public class TaskLibrary {
	private String taskName;
	private String inputType;
	private String outputType;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public TaskLibrary(String taskName, String inputType, String outputType) {
		super();
		this.taskName = taskName;
		this.inputType = inputType;
		this.outputType = outputType;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
}
