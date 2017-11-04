package com.distributedpipeline.persistence.domain;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="WORKFLOW")
public class Workflow {	
	
	@Id
	@NotNull
	private String workFlowName;
	
	private List<Tasks> tasks;
	
	
	/*--------------------- Getters and Setters for the fields -----------------------------*/
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	public List<Tasks> getTasks() {
		return tasks;
	}
	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}
	
	
}
	
