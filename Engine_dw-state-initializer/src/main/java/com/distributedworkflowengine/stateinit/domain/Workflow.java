package com.distributedworkflowengine.stateinit.domain;

import java.util.List;

//import javax.persistence.Column;

public class Workflow {
	private String workFlowName;
	
	private List<Task> tasks;

	public String getWorkFlowName() {
		return workFlowName;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
