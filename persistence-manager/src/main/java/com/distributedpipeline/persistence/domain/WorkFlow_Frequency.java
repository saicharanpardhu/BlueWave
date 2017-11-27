package com.distributedpipeline.persistence.domain;
import java.util.Date;

public class WorkFlow_Frequency {

	private String workFlowName;
	private Integer frequency;
	private Date[] executionTime;
	
	public WorkFlow_Frequency() {
		super();
	}
	public WorkFlow_Frequency(String workFlowName, Integer frequency, Date[] executionTime) {
		super();
		this.workFlowName = workFlowName;
		this.frequency = frequency;
		this.executionTime = executionTime;
	}
	
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Date[] getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date[] executionTime) {
		this.executionTime = executionTime;
	}
	
}