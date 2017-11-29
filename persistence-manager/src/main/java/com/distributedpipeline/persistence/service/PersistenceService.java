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
	
	/*----------------------Delete a workflow--------------------------------------------*/
    public void deleteWorkflow(String workFlowName, String workflowOwner) throws WorkflowNotFoundException;
    
    /*----------------------Update a workflow--------------------------------------------*/
    public void updateWorkflow(Workflow workflow) throws WorkflowNotFoundException;
    
    /*----------------------- Method to get workflow by owner -----------------------------*/
	public List<Workflow> getAllWorkflowOfOwner(String userName);
	
	/*----------------------- Method to get workflow by name and owner -----------------------------*/
	public Workflow getWorkflowByNameAndUserName(String workFlowName,String owner) throws WorkflowNotFoundException;
    
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
	
	/*----------------------- Method to get Top Ten Job details by user name -----------------------------*/
	public List<JobIdDetails> getTopJobDetails(String userName, int pageNumber, int size);
	
	/*----------------------- Method to get count Job details by user name -----------------------------*/
	public int getCountJobDetails(String userName);
		
    
}
