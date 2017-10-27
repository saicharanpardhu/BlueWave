package com.distributedpipeline.persistence.service;
import java.util.List;

import com.distributedpipeline.persistence.domain.PersistenceModel;
import com.distributedpipeline.persistence.exceptions.WorkflowAlreadyExists;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;

public interface PersistenceService {
	/*-------------Retrieve by id ----------------- */
	public PersistenceModel getWorkflow(long id) throws WorkflowNotFoundException;
	
	/*-------------Retrieve all ---------------------*/ 
	public List<PersistenceModel> getWorkflow() throws WorkflowNotFoundException;
	
	/*------------Add a new workflow------------------*/
	public PersistenceModel addWorkflow(PersistenceModel persistencemodel); 
	
	/*------------Update a workflow-------------------*/  
	public PersistenceModel updateWorkflow(PersistenceModel persistencemodel) throws WorkflowAlreadyExists;
	
	/*------------Delete a workflow-------------------*/
    public boolean deleteWorkflow(int id); 

}
