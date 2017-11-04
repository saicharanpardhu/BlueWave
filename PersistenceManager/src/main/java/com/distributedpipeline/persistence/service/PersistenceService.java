package com.distributedpipeline.persistence.service;

import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;

public interface PersistenceService {
	/*---------------------Retrieve workflow by workflowname---------------------------- */
	public Workflow getWorkflowByName(String workFlowName) throws WorkflowNotFoundException;
	/*---------------------Retrieve all workflows----------------------------------------*/ 
	public Iterable<Workflow> getWorkflow() throws WorkflowNotFoundException;
	
	/*----------------------Add a new workflow-------------------------------------------*/
	public Workflow addWorkflow(Workflow workflow); 
	
	/*----------------------Update a workflow--------------------------------------------*/  
	public Workflow updateWorkflow(Workflow workFlow) ;
	
	/*----------------------Delete a workflow--------------------------------------------*/
    public boolean deleteWorkflow(String workFlowName) throws WorkflowNotFoundException;
    
}
