package in.stackroute.dw.domain;

import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class Model {
	private String jobId;
	private Map<String,Task> listOfTasks;
	
	
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	

	
	public Map<String, Task> getListOfTasks() {
		return listOfTasks;
	}
	public void setListOfTasks(Map<String, Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}
	public Model() {}
	
	
}
