package com.distributedpipeline.persistence.service;

import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.TaskLibraryNotFoundException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;

public interface PersistenceService {
	/*---------------------Retrieve workflow by workflowname---------------------------- */
	public Workflow getWorkflowByName(String workFlowName) throws WorkflowNotFoundException;
	/*---------------------Retrieve all workflows----------------------------------------*/ 
	public Iterable<Workflow> getWorkflow() throws WorkflowNotFoundException;
	
	/*----------------------Add a new workflow-------------------------------------------*/
	public String addWorkflow(Workflow workflow); 
	
	/*----------------------Update a workflow--------------------------------------------*/  
	public Workflow updateWorkflow(Workflow workFlow) ;
	
	/*----------------------Delete a workflow--------------------------------------------*/
    public boolean deleteWorkflow(String workFlowName) throws WorkflowNotFoundException;
    
    /*-------------------- Check Permissions for different users ---------------------- */
    public String userPermissions(String workFlowName, String userName);
	
	
    /*-------------------Retrieve tasklibrary by taskname------------------------------- */    
    public TaskLibrary gettaskLibraryByName(String taskName) throws TaskLibraryNotFoundException;

    /*---------------------Retrieve all tasklibraries----------------- ----------------- */
	public Iterable<TaskLibrary> getTaskLibrary() throws TaskLibraryNotFoundException;
	
	/*-----------------------Add a new tasklibrary ------------------------------------- */
	public TaskLibrary addTaskLibrary(TaskLibrary taskLibrary);
	
	/*------------------------Update a tasklibrary ------------------------------------- */
	public TaskLibrary updateTaskLibrary(TaskLibrary taskLibrary) ;	
	/*--------------------------Delete a tasklibrary ----------------------------------- */
	public boolean deleteTaskLibrary(String taskName)  throws TaskLibraryNotFoundException;
    
}
