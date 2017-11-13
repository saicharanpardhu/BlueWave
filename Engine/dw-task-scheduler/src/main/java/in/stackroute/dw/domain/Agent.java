package in.stackroute.dw.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Agent {

	private String jobId;
	private String taskname;
	private String input;
	
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public Agent(String jobId, String taskname, String input) {
		super();
		this.jobId = jobId;
		this.taskname = taskname;
		this.input = input;
	}
	public Agent() {}
}
