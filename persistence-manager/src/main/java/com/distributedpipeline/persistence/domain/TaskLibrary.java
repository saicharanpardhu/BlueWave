package com.distributedpipeline.persistence.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TASKLIBRARY")
public class TaskLibrary{
	
	@Id
	@NotNull
	private String taskName;
	private String inputType;
	private String outputType;
	private String taskCommand;	
	
	/*--------------------- Getters and Setters for the fields -----------------------------*/
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
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public String getTaskCommand() {
		return taskCommand;
	}
	public void setTaskCommand(String taskCommand) {
		this.taskCommand = taskCommand;
	}
	
	/*-----------------------     Constructors -----------------------------------------------*/
	public TaskLibrary() {
		super();
	}
	public TaskLibrary(String taskName, String inputType, String outputType, String taskCommand) {
		super();
		this.taskName = taskName;
		this.inputType = inputType;
		this.outputType = outputType;
		this.taskCommand = taskCommand;
	}
	
	
	
	
	
}
