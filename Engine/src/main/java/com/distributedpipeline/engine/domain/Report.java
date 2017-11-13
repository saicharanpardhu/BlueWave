package com.distributedpipeline.engine.domain;

import java.sql.Timestamp;

import javax.persistence.Column; 

public class Report { 
    private String jobID;
    
    private String workflowName;
	 
	private Timestamp timestamp;
	 
	private String userid;
	 
	private String message;
	 
	private String severity;
}
