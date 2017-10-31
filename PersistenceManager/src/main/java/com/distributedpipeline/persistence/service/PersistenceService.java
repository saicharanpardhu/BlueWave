package com.distributedpipeline.persistence.service;
import java.util.List;

import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.WorkflowAlreadyExistsException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;

public interface PersistenceService {
	/*-------------Retrieve by id ----------------- */
	public Workflow getWorkflow(long id) throws WorkflowNotFoundException;
	
	/*-------------Retrieve all ---------------------*/ 
	public List<Workflow> getWorkflow() throws WorkflowNotFoundException;
	
	/*------------Add a new workflow------------------*/
	public Workflow addWorkflow(Workflow persistencemodel); 
	
	/*------------Update a workflow-------------------*/  
	public Workflow updateWorkflow(Workflow persistencemodel) throws WorkflowAlreadyExistsException;
	
	/*------------Delete a workflow-------------------*/
    public boolean deleteWorkflow(int id); 

}
