package com.distributedworkflowengine.stateinit.domain;

public class TaskResponse {
	private String taskName;
	private Integer exitCode;
	private String stdout;
	private String stderr;
	private String jobID;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getExitCode() {
		return exitCode;
	}
	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}
	public String getStdout() {
		return stdout;
	}
	public void setStdout(String stdout) {
		this.stdout = stdout;
	}
	public String getStderr() {
		return stderr;
	}
	public void setStderr(String stderr) {
		this.stderr = stderr;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public TaskResponse(String taskName, Integer exitCode, String stdout, String stderr, String jobID) {
		super();
		this.taskName = taskName;
		this.exitCode = exitCode;
		this.stdout = stdout;
		this.stderr = stderr;
		this.jobID = jobID;
	}
	
	
}
