package com.distributedpipeline.engine.domain;

import javax.persistence.Column;

public class Workflow {
	@Column(name="workflowName")
	private String workflowName;
	
	@Column(name="tasks")
	private Task[] tasks;
}
