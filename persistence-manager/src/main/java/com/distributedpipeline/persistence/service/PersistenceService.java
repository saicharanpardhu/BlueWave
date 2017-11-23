package com.distributedpipeline.persistence.service;

import java.util.List;
import java.util.Map;

import com.distributedpipeline.persistence.domain.JobIdDetails;
import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Tasks;
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
    
    /*-------------------------- Method to authenticate a user ----------------------------*/
    public String userPermissions(String workFlowName, String userName);	
    
    /*-------------------------- Method to get tasks inside workflow -----------------------------*/
    public List<String> getTasksOfWorkflow(String workFlowName);
  
    /*-------------------------- Method to get details of a task in a workflow -----------------------*/
    public Tasks getDetailsOfTask(String workFlowName, String task_name);
	
    /*-------------------Retrieve tasklibrary by taskname------------------------------- */    
    public TaskLibrary gettaskLibraryByName(String taskName) throws TaskLibraryNotFoundException;

    /*---------------------Retrieve all tasklibraries----------------- ----------------- */
	public Iterable<TaskLibrary> getTaskLibrary() throws TaskLibraryNotFoundException;
	
	/*-----------------------Add a new tasklibrary ------------------------------------- */
	public TaskLibrary addTaskLibrary(TaskLibrary taskLibrary);
	
	/*----------------------- Get command for a task name ------------------------------------- */
	public String getTaskCommand(String taskName);
	
	/*------------------------Update a tasklibrary ------------------------------------- */
	public TaskLibrary updateTaskLibrary(TaskLibrary taskLibrary) ;	
	/*--------------------------Delete a tasklibrary ----------------------------------- */
	public boolean deleteTaskLibrary(String taskName)  throws TaskLibraryNotFoundException;
	
	/*----------------------- Method to get Job details by job id -----------------------------*/
	public JobIdDetails getJobDetailsByJobId(String jobId);
	
	/*----------------------- Method to get Job details by user name -----------------------------*/
	public Iterable<JobIdDetails> getJobDetailsByUserName(String userName);
	
	/*----------------------- Method to post Job details -----------------------------*/
	public String addJobDetails(JobIdDetails jobIdDetails);
	
	/*----------------------- Method to get all Job details -----------------------------*/
	public Iterable<JobIdDetails> getAllJobDetails();
    
}
