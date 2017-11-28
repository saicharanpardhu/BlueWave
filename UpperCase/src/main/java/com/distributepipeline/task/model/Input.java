package com.distributepipeline.task.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public class Input implements Serializable {
	
	private String jobId;
	private String taskname;
	private String[] input;
	private String userName;
	private String type;
	private String workFlowName;
	private String[] commands;
	private String folderPath;
	
	public Input(String jobId, String taskname, String[] input, String userName, String type) {
		super();
		this.jobId = jobId;
		this.taskname = taskname;
		this.input = input;
		this.userName = userName;
		this.type=type;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	public String[] getCommands() {
		return commands;
	}
	public void setCommands(String[] commands) {
		this.commands = commands;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	
}
