package com.distributedpipeline.engine.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
 
public class Trigger { 
	private String workflowName;
	 
	private String jobID;
	 
	private Object[] payload; 
	
}
