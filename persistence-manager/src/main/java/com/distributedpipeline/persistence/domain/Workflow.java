package com.distributedpipeline.persistence.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Workflow")
public class Workflow {	
	
	@Id
	private String id;
	@NotNull
	private String workFlowName;
	private String owner;
	private Date timeStamp;
	private String description;
    private String[] canViewUser;
    private String[] canExecuteUser;
    private String[] canEditUser;
	private String workFlowStatus;
	private List<Date> executionTime;
	private String frequency;
    private Map<String,Tasks> tasks;
	
	
	/*--------------------- Getters and Setters for the fields -----------------------------*/
	

	public String getId() {
		return id;
	}

	public String[] getCanEditUser() {
		return canEditUser;
	}
	
	public String getWorkFlowName() {
		return workFlowName;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}


	public void setCanEditUser(String[] canEditUser) {
		this.canEditUser = canEditUser;
	}
	
	
	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String[] getCanViewUser() {
		return canViewUser;
	}


	public void setCanViewUser(String[] canViewUser) {
		this.canViewUser = canViewUser;
	}


	public String[] getCanExecuteUser() {
		return canExecuteUser;
	}


	public void setCanExecuteUser(String[] canExecuteUser) {
		this.canExecuteUser = canExecuteUser;
	}


	public String getWorkFlowStatus() {
		return workFlowStatus;
	}


	public void setWorkFlowStatus(String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	public Map<String, Tasks> getTasks() {
		return tasks;
	}


	public void setTasks(Map<String, Tasks> tasks) {
		this.tasks = tasks;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public List<Date> getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(List<Date> executionTime) {
		this.executionTime = executionTime;
	}

 /*------------------------------- Constructors -----------------------------------------*/
	public Workflow(String workFlowName, String owner, String[] canViewUser, String[] canExecuteUser,
			String workFlowStatus, Map<String, Tasks> tasks) {
		super();
		this.workFlowName = workFlowName;
		this.owner = owner;
		this.canViewUser = canViewUser;
		this.canExecuteUser = canExecuteUser;
		this.workFlowStatus = workFlowStatus;
		this.tasks = tasks;
	}
	
	public Workflow() {
		super();
	}
	
	
}
	
