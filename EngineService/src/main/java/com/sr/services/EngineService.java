package com.sr.services;



import com.sr.domain.EngineModel;
import com.sr.exceptions.WorkflowNotFoundException;

//Interface for all the services

public interface EngineService {

	public EngineModel getWorkflow() throws WorkflowNotFoundException; 
	
}
