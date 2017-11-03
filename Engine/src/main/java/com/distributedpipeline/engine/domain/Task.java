package com.distributedpipeline.engine.domain;

public class Task {
	private String taskName;
	private String type;
	private String[] depends_on;
	private String[] output;
	private String[] input;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getDepends_on() {
		return depends_on;
	}
	public void setDepends_on(String[] depends_on) {
		this.depends_on = depends_on;
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
	public Task(String taskName, String type, String[] depends_on, String[] output, String[] input) {
		super();
		this.taskName = taskName;
		this.type = type;
		this.depends_on = depends_on;
		this.output = output;
		this.input = input;
	}
	
}
