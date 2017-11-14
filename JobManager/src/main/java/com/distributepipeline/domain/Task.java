package com.distributepipeline.domain;

import org.springframework.stereotype.Component;

@Component
public class Task {

	//Fields for Task model
	
	private String type;
    private String status;
    private String depends_on[];
    private String input[];
    private String output[];
    
    //getters and setters for Task fields
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getDepends_on() {
		return depends_on;
	}
	public void setDepends_on(String[] depends_on) {
		this.depends_on = depends_on;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}
	public String[] getOutput() {
		return output;
	}
	public void setOutput(String[] output) {
		this.output = output;
	}
	
	//Constructor of Task class
	
	public Task(String type, String status, String[] depends_on, String[] input, String[] output) {
		super();
		this.type = type;
		this.status = status;
		this.depends_on = depends_on;
		this.input = input;
		this.output = output;
	}
	public Task() {
		super();
	}
	
}
