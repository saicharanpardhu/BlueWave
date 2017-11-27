package com.distributedworkflowengine.stateinit.domain;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class Task2 {
	
	private String type;
	private String status;
	private String[] depends_on;
	private String[] output;
	private String[] input;
	private String[] commands;
	private String folderPath;
	
	
	/*------------------------ Getters and Setters for the fields-----------------------------*/
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
	public String[] getDepends_on() {
		return depends_on;
	}

	public void setDepends_on(String[] depends_on) {
		this.depends_on = depends_on;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getOutput() {
		return output;
	}
	public void setOutput(String[] output) {
		this.output = output;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}
	
    /*------------------------------- Constructors -----------------------------------------*/
	public Task2() {
		super();
	}


	public Task2(String type, String status, String[] depends_on, String[] output, String[] input, String[] commands,
			String folderPath) {
		super();
		this.type = type;
		this.status = status;
		this.depends_on = depends_on;
		this.output = output;
		this.input = input;
		this.commands = commands;
		this.folderPath = folderPath;
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